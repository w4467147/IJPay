package com.ijpay.demo.controller.wxpay;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ijpay.core.IJPayHttpResponse;
import com.ijpay.core.enums.RequestMethodEnum;
import com.ijpay.core.kit.AesUtil;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.PayKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.core.utils.DateTimeZoneUtil;
import com.ijpay.demo.entity.WxPayV3Bean;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.enums.WxDomainEnum;
import com.ijpay.wxpay.enums.v3.Apply4SubApiEnum;
import com.ijpay.wxpay.enums.v3.BasePayApiEnum;
import com.ijpay.wxpay.enums.v3.ComplaintsApiEnum;
import com.ijpay.wxpay.enums.v3.OtherApiEnum;
import com.ijpay.wxpay.enums.v3.PayGiftActivityApiEnum;
import com.ijpay.wxpay.enums.v3.PayScoreApiEnum;
import com.ijpay.wxpay.model.v3.Amount;
import com.ijpay.wxpay.model.v3.Payer;
import com.ijpay.wxpay.model.v3.RefundAmount;
import com.ijpay.wxpay.model.v3.RefundGoodsDetail;
import com.ijpay.wxpay.model.v3.RefundModel;
import com.ijpay.wxpay.model.v3.UnifiedOrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>IJPay ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????</p>
 *
 * <p>???????????????????????? mvc ??????????????????????????????????????????????????????????????????????????????????????????????????????????????? </p>
 *
 * <p>IJPay ?????????: 723992875???864988890</p>
 *
 * <p>Node.js ???: <a href="https://gitee.com/javen205/TNWX">https://gitee.com/javen205/TNWX</a></p>
 *
 * <p>???????????? v3 ????????????</p>
 *
 * @author Javen
 */
@Controller
@RequestMapping("/v3")
public class WxPayV3Controller {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final static int OK = 200;

	@Resource
	WxPayV3Bean wxPayV3Bean;

	String serialNo;
	String platSerialNo;


	@RequestMapping("")
	@ResponseBody
	public String index() {
		log.info(wxPayV3Bean.toString());
		try {
			String classPath = "classpath:/dev/apiclient_cert.p12";
			String v3 = "classpath:/dev/wxpay_v3.properties";
			String absolutePath = PayKit.getAbsolutePath(classPath);
			log.info("absolutePath:{}", absolutePath);
			InputStream inputStream = PayKit.getCertFileInputStream(v3);
			if (null != inputStream) {
				ByteArrayOutputStream result = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int length;
				while ((length = inputStream.read(buffer)) != -1) {
					result.write(buffer, 0, length);
				}
				String str = result.toString();
				log.info("file content:{}", str);
			}
		} catch (Exception e) {
			log.error("???????????????", e);
		}
		return ("???????????? IJPay ?????????????????? Api-v3 -By Javen  <br/><br>  ????????????723992875???864988890");
	}

	@RequestMapping("/getSerialNumber")
	@ResponseBody
	public String serialNumber() {
		return getSerialNumber();
	}

	@RequestMapping("/getPlatSerialNumber")
	@ResponseBody
	public String platSerialNumber() {
		return getPlatSerialNumber();
	}

	private String getSerialNumber() {
		if (StrUtil.isEmpty(serialNo)) {
			// ?????????????????????
			X509Certificate certificate = PayKit.getCertificate(wxPayV3Bean.getCertPath());
			if (null != certificate) {
				serialNo = certificate.getSerialNumber().toString(16).toUpperCase();
				// ????????????????????????????????????
				boolean isValid = PayKit.checkCertificateIsValid(certificate, wxPayV3Bean.getMchId(), -2);
				log.info("?????????????????? {} ?????????????????? {}", isValid, DateUtil.format(certificate.getNotAfter(), DatePattern.NORM_DATETIME_PATTERN));
			}
//            System.out.println("??????????????????:\n" + certificate.toString());
//            // ????????????????????????????????????????????????
//            System.out.println("???????????????:" + certificate.getSerialNumber().toString(16));
//            System.out.println("?????????:" + certificate.getVersion());
//            System.out.println("????????????" + certificate.getIssuerDN());
//            System.out.println("?????????????????????" + certificate.getNotBefore());
//            System.out.println("?????????????????????" + certificate.getNotAfter());
//            System.out.println("????????????" + certificate.getSubjectDN());
//            System.out.println("???????????????" + certificate.getSigAlgName());
//            System.out.println("?????????" + certificate.getSignature().toString());
		}
		System.out.println("serialNo:" + serialNo);
		return serialNo;
	}

	private String getPlatSerialNumber() {
		if (StrUtil.isEmpty(platSerialNo)) {
			// ???????????????????????????
			X509Certificate certificate = PayKit.getCertificate(FileUtil.getInputStream(wxPayV3Bean.getPlatformCertPath()));
			platSerialNo = certificate.getSerialNumber().toString(16).toUpperCase();
		}
		System.out.println("platSerialNo:" + platSerialNo);
		return platSerialNo;
	}

	private String savePlatformCert(String associatedData, String nonce, String cipherText, String certPath) {
		try {
			AesUtil aesUtil = new AesUtil(wxPayV3Bean.getApiKey3().getBytes(StandardCharsets.UTF_8));
			// ????????????????????????
			// encrypt_certificate ??????  associated_data nonce  ciphertext
			String publicKey = aesUtil.decryptToString(
				associatedData.getBytes(StandardCharsets.UTF_8),
				nonce.getBytes(StandardCharsets.UTF_8),
				cipherText
			);
			// ????????????
			FileWriter writer = new FileWriter(certPath);
			writer.write(publicKey);
			// ???????????????????????????
			X509Certificate certificate = PayKit.getCertificate(new ByteArrayInputStream(publicKey.getBytes()));
			return certificate.getSerialNumber().toString(16).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}


	@RequestMapping("/platformCert")
	@ResponseBody
	public String platformCert() {
		try {
			String associatedData = "certificate";
			String nonce = "80d28946a64a";
			String cipherText = "DwAqW4+4TeUaOEylfKEXhw+XqGh/YTRhUmLw/tBfQ5nM9DZ9d+9aGEghycwV1jwo52vXb/t6ueBvBRHRIW5JgDRcXmTHw9IMTrIK6HxTt2qiaGTWJU9whsF+GGeQdA7gBCHZm3AJUwrzerAGW1mclXBTvXqaCl6haE7AOHJ2g4RtQThi3nxOI63/yc3WaiAlSR22GuCpy6wJBfljBq5Bx2xXDZXlF2TNbDIeodiEnJEG2m9eBWKuvKPyUPyClRXG1fdOkKnCZZ6u+ipb4IJx28n3MmhEtuc2heqqlFUbeONaRpXv6KOZmH/IdEL6nqNDP2D7cXutNVCi0TtSfC7ojnO/+PKRu3MGO2Z9q3zyZXmkWHCSms/C3ACatPUKHIK+92MxjSQDc1E/8faghTc9bDgn8cqWpVKcL3GHK+RfuYKiMcdSkUDJyMJOwEXMYNUdseQMJ3gL4pfxuQu6QrVvJ17q3ZjzkexkPNU4PNSlIBJg+KX61cyBTBumaHy/EbHiP9V2GeM729a0h5UYYJVedSo1guIGjMZ4tA3WgwQrlpp3VAMKEBLRJMcnHd4pH5YQ/4hiUlHGEHttWtnxKFwnJ6jHr3OmFLV1FiUUOZEDAqR0U1KhtGjOffnmB9tymWF8FwRNiH2Tee/cCDBaHhNtfPI5129SrlSR7bZc+h7uzz9z+1OOkNrWHzAoWEe3XVGKAywpn5HGbcL+9nsEVZRJLvV7aOxAZBkxhg8H5Fjt1ioTJL+qXgRzse1BX1iiwfCR0fzEWT9ldDTDW0Y1b3tb419MhdmTQB5FsMXYOzqp5h+Tz1FwEGsa6TJsmdjJQSNz+7qPSg5D6C2gc9/6PkysSu/6XfsWXD7cQkuZ+TJ/Xb6Q1Uu7ZB90SauA8uPQUIchW5zQ6UfK5dwMkOuEcE/141/Aw2rlDqjtsE17u1dQ6TCax/ZQTDQ2MDUaBPEaDIMPcgL7fCeijoRgovkBY92m86leZvQ+HVbxlFx5CoPhz4a81kt9XJuEYOztSIKlm7QNfW0BvSUhLmxDNCjcxqwyydtKbLzA+EBb2gG4ORiH8IOTbV0+G4S6BqetU7RrO+/nKt21nXVqXUmdkhkBakLN8FUcHygyWnVxbA7OI2RGnJJUnxqHd3kTbzD5Wxco4JIQsTOV6KtO5c960oVYUARZIP1SdQhqwELm27AktEN7kzg/ew/blnTys/eauGyw78XCROb9F1wbZBToUZ7L+8/m/2tyyyqNid+sC9fYqJoIOGfFOe6COWzTI/XPytCHwgHeUxmgk7NYfU0ukR223RPUOym6kLzSMMBKCivnNg68tbLRJHEOpQTXFBaFFHt2qpceJpJgw5sKFqx3eQnIFuyvA1i8s2zKLhULZio9hpsDJQREOcNeHVjEZazdCGnbe3Vjg7uqOoVHdE/YbNzJNQEsB3/erYJB+eGzyFwFmdAHenG5RE6FhCutjszwRiSvW9F7wvRK36gm7NnVJZkvlbGwh0UHr0pbcrOmxT81xtNSvMzT0VZNLTUX2ur3AGLwi2ej8BIC0H41nw4ToxTnwtFR1Xy55+pUiwpB7JzraA08dCXdFdtZ72Tw/dNBy5h1P7EtQYiKzXp6rndfOEWgNOsan7e1XRpCnX7xoAkdPvy40OuQ5gNbDKry5gVDEZhmEk/WRuGGaX06CG9m7NfErUsnQYrDJVjXWKYuARd9R7W0aa5nUXqz/Pjul/LAatJgWhZgFBGXhNr9iAoade/0FPpBj0QWa8SWqKYKiOqXqhfhppUq35FIa0a1Vvxcn3E38XYpVZVTDEXcEcD0RLCu/ezdOa6vRcB7hjgXFIRZQAka0aXnQxwOZwE2Rt3yWXqc+Q1ah2oOrg8Lg3ETc644X9QP4FxOtDwz/A==";
			return savePlatformCert(associatedData, nonce, cipherText, wxPayV3Bean.getPlatformCertPath());
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("/get")
	@ResponseBody
	public String v3Get() {
		// ????????????????????????
		try {
			IJPayHttpResponse response = WxPayApi.v3(
				RequestMethodEnum.GET,
				WxDomainEnum.CHINA.toString(),
				OtherApiEnum.GET_CERTIFICATES.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				""
			);

			String timestamp = response.getHeader("Wechatpay-Timestamp");
			String nonceStr = response.getHeader("Wechatpay-Nonce");
			String serialNumber = response.getHeader("Wechatpay-Serial");
			String signature = response.getHeader("Wechatpay-Signature");

			String body = response.getBody();
			int status = response.getStatus();

			log.info("serialNumber: {}", serialNumber);
			log.info("status: {}", status);
			log.info("body: {}", body);
			int isOk = 200;
			if (status == isOk) {
				JSONObject jsonObject = JSONUtil.parseObj(body);
				JSONArray dataArray = jsonObject.getJSONArray("data");
				// ????????????????????????????????????
				JSONObject encryptObject = dataArray.getJSONObject(0);
				JSONObject encryptCertificate = encryptObject.getJSONObject("encrypt_certificate");
				String associatedData = encryptCertificate.getStr("associated_data");
				String cipherText = encryptCertificate.getStr("ciphertext");
				String nonce = encryptCertificate.getStr("nonce");
				String serialNo = encryptObject.getStr("serial_no");
				final String platSerialNo = savePlatformCert(associatedData, nonce, cipherText, wxPayV3Bean.getPlatformCertPath());
				log.info("?????????????????????: {} serialNo: {}", platSerialNo, serialNo);
			}
			// ???????????????????????????????????????????????????????????????
			boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
			System.out.println("verifySignature:" + verifySignature);
			return body;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/nativePay")
	@ResponseBody
	public String nativePay() {
		try {
			String timeExpire = DateTimeZoneUtil.dateToTimeZone(System.currentTimeMillis() + 1000 * 60 * 3);
			UnifiedOrderModel unifiedOrderModel = new UnifiedOrderModel()
				.setAppid(wxPayV3Bean.getAppId())
				.setMchid(wxPayV3Bean.getMchId())
				.setDescription("IJPay ?????????????????????")
				.setOut_trade_no(PayKit.generateStr())
				.setTime_expire(timeExpire)
				.setAttach("???????????????????????? https://gitee.com/javen205/TNWX")
				.setNotify_url(wxPayV3Bean.getDomain().concat("/v3/payNotify"))
				.setAmount(new Amount().setTotal(1));

			log.info("?????????????????? {}", JSONUtil.toJsonStr(unifiedOrderModel));
			IJPayHttpResponse response = WxPayApi.v3(
				RequestMethodEnum.POST,
				WxDomainEnum.CHINA.toString(),
				BasePayApiEnum.NATIVE_PAY.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				JSONUtil.toJsonStr(unifiedOrderModel)
			);
			log.info("?????????????????? {}", response);
			// ???????????????????????????????????????????????????????????????
			boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
			log.info("verifySignature: {}", verifySignature);
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("/jsApiPay")
	@ResponseBody
	public String jsApiPay(@RequestParam(value = "openId", required = false, defaultValue = "o-_-itxuXeGW3O1cxJ7FXNmq8Wf8") String openId) {
		try {
			String timeExpire = DateTimeZoneUtil.dateToTimeZone(System.currentTimeMillis() + 1000 * 60 * 3);
			UnifiedOrderModel unifiedOrderModel = new UnifiedOrderModel()
				.setAppid(wxPayV3Bean.getAppId())
				.setMchid(wxPayV3Bean.getMchId())
				.setDescription("IJPay ?????????????????????")
				.setOut_trade_no(PayKit.generateStr())
				.setTime_expire(timeExpire)
				.setAttach("???????????????????????? https://gitee.com/javen205/TNWX")
				.setNotify_url(wxPayV3Bean.getDomain().concat("/v3/payNotify"))
				.setAmount(new Amount().setTotal(1))
				.setPayer(new Payer().setOpenid(openId));

			log.info("?????????????????? {}", JSONUtil.toJsonStr(unifiedOrderModel));
			IJPayHttpResponse response = WxPayApi.v3(
				RequestMethodEnum.POST,
				WxDomainEnum.CHINA.toString(),
				BasePayApiEnum.JS_API_PAY.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				JSONUtil.toJsonStr(unifiedOrderModel)
			);
			log.info("?????????????????? {}", response);
			// ???????????????????????????????????????????????????????????????
			boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
			log.info("verifySignature: {}", verifySignature);
			if (response.getStatus() == OK && verifySignature) {
				String body = response.getBody();
				JSONObject jsonObject = JSONUtil.parseObj(body);
				String prepayId = jsonObject.getStr("prepay_id");
				Map<String, String> map = WxPayKit.jsApiCreateSign(wxPayV3Bean.getAppId(), prepayId, wxPayV3Bean.getKeyPath());
				log.info("??????????????????:{}", map);
				return JSONUtil.toJsonStr(map);
			}
			return JSONUtil.toJsonStr(response);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}


	@RequestMapping("/put")
	@ResponseBody
	public String put() {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("url", "https://gitee.com/javen205/IJPay");

			IJPayHttpResponse response = WxPayApi.v3(
				RequestMethodEnum.PUT,
				WxDomainEnum.CHINA.toString(),
				ComplaintsApiEnum.COMPLAINTS_NOTIFICATION.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				JSONUtil.toJsonStr(params)
			);
			// ???????????????????????????????????????????????????????????????
			boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
			log.info("verifySignature: {}", verifySignature);
			log.info("?????? {}", response);
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}


	@RequestMapping("/getParams")
	@ResponseBody
	public String payScoreServiceOrder() {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("service_id", "500001");
			params.put("appid", "wxd678efh567hg6787");
			params.put("out_order_no", "1234323JKHDFE1243252");

			IJPayHttpResponse result = WxPayApi.v3(
				RequestMethodEnum.GET,
				WxDomainEnum.CHINA.toString(),
				PayScoreApiEnum.PAY_SCORE_SERVICE_ORDER.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				params
			);
			System.out.println(result);
			return JSONUtil.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String v3Delete() {
		// ??????/??????/??????/????????????????????????
		try {
			HashMap<String, String> hashMap = new HashMap<>(12);
			hashMap.put("url", "https://qq.com");
			IJPayHttpResponse result = WxPayApi.v3(
				RequestMethodEnum.POST,
				WxDomainEnum.CHINA.toString(),
				ComplaintsApiEnum.COMPLAINTS_NOTIFICATION.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				JSONUtil.toJsonStr(hashMap)
			);
			System.out.println(result);

			result = WxPayApi.v3(
				RequestMethodEnum.DELETE,
				WxDomainEnum.CHINA.toString(),
				ComplaintsApiEnum.COMPLAINTS_NOTIFICATION.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				""
			);
			// ???????????????????????????????????????????????????????????????
			boolean verifySignature = WxPayKit.verifySignature(result, wxPayV3Bean.getPlatformCertPath());
			System.out.println("verifySignature:" + verifySignature);
			// ?????????????????? 204 ??????????????????
			System.out.println(result);
			return JSONUtil.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String v3Upload() {
		// v3 ??????????????????
		try {
			String filePath = "/Users/Javen/Documents/pic/cat.png";

			File file = FileUtil.newFile(filePath);
			String sha256 = SecureUtil.sha256(file);

			HashMap<Object, Object> map = new HashMap<>();
			map.put("filename", file.getName());
			map.put("sha256", sha256);
			String body = JSONUtil.toJsonStr(map);

			System.out.println(body);

			IJPayHttpResponse result = WxPayApi.v3(
				WxDomainEnum.CHINA.toString(),
				OtherApiEnum.MERCHANT_UPLOAD_MEDIA.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				body,
				file
			);
			// ???????????????????????????????????????????????????????????????
			boolean verifySignature = WxPayKit.verifySignature(result, wxPayV3Bean.getPlatformCertPath());
			System.out.println("verifySignature:" + verifySignature);
			System.out.println(result);
			return JSONUtil.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("/post")
	@ResponseBody
	public String payGiftActivity() {
		// ????????????-????????????
		try {
			String urlSuffix = String.format(PayGiftActivityApiEnum.PAY_GIFT_ACTIVITY_TERMINATE.toString(), "10028001");
			System.out.println(urlSuffix);
			IJPayHttpResponse result = WxPayApi.v3(
				RequestMethodEnum.POST,
				WxDomainEnum.CHINA.toString(),
				urlSuffix,
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				""
			);
			System.out.println(result);
			return JSONUtil.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("/sensitive")
	@ResponseBody
	public String sensitive() {
		// ????????????????????????
		try {
			String body = "??????????????????";

			IJPayHttpResponse result = WxPayApi.v3(
				RequestMethodEnum.POST,
				WxDomainEnum.CHINA.toString(),
				Apply4SubApiEnum.APPLY_4_SUB.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				getPlatSerialNumber(),
				wxPayV3Bean.getKeyPath(),
				body
			);
			System.out.println(result);
			return JSONUtil.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("/cipher")
	@ResponseBody
	public String cipher() {
		try {
			// ??????????????????
			X509Certificate certificate = PayKit.getCertificate(FileUtil.getInputStream(wxPayV3Bean.getPlatformCertPath()));
			String encrypt = PayKit.rsaEncryptOAEP("IJPay", certificate);
			System.out.println(encrypt);
			// ??????????????????
			String encryptStr = "";
			PrivateKey privateKey = PayKit.getPrivateKey(wxPayV3Bean.getKeyPath());
			String decrypt = PayKit.rsaDecryptOAEP(encryptStr, privateKey);
			System.out.println(decrypt);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return null;
	}

	/**
	 * ??????????????????
	 *
	 * @param billDate 2020-06-14 ?????????????????????????????????????????????????????????????????????????????????
	 * @return ????????????????????????
	 */
	@RequestMapping("/tradeBill")
	@ResponseBody
	public String tradeBill(@RequestParam(value = "billDate", required = false) String billDate) {
		try {
			if (StrUtil.isEmpty(billDate)) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DATE, -1);
				billDate = DateUtil.format(calendar.getTime(), "YYYY-MM-dd");
			}
			Map<String, String> params = new HashMap<>(12);
			params.put("bill_date", billDate);
			params.put("bill_type", "ALL");
			params.put("tar_type", "GZIP");

			IJPayHttpResponse result = WxPayApi.v3(
				RequestMethodEnum.GET,
				WxDomainEnum.CHINA.toString(),
				BasePayApiEnum.TRADE_BILL.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				params
			);
			// ???????????????????????????????????????????????????????????????
			boolean verifySignature = WxPayKit.verifySignature(result, wxPayV3Bean.getPlatformCertPath());
			log.info("verifySignature: {}", verifySignature);
			log.info("result:{}", result);
			return JSONUtil.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	/**
	 * ??????????????????
	 *
	 * @param billDate 2020-06-14 ?????????????????????????????????????????????????????????????????????????????????
	 * @return ????????????????????????
	 */
	@RequestMapping("/fundFlowBill")
	@ResponseBody
	public String fundFlowBill(@RequestParam(value = "billDate", required = false) String billDate) {
		try {
			if (StrUtil.isEmpty(billDate)) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DATE, -1);
				billDate = DateUtil.format(calendar.getTime(), "YYYY-MM-dd");
			}
			Map<String, String> params = new HashMap<>(12);
			params.put("bill_date", billDate);
			params.put("account_type", "BASIC");

			IJPayHttpResponse result = WxPayApi.v3(
				RequestMethodEnum.GET,
				WxDomainEnum.CHINA.toString(),
				BasePayApiEnum.FUND_FLOW_BILL.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				params
			);
			// ???????????????????????????????????????????????????????????????
			boolean verifySignature = WxPayKit.verifySignature(result, wxPayV3Bean.getPlatformCertPath());
			log.info("verifySignature: {}", verifySignature);
			log.info("result:{}", result);
			return JSONUtil.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("/billDownload")
	@ResponseBody
	public String billDownload(@RequestParam(value = "token") String token,
							   @RequestParam(value = "tarType", required = false) String tarType) {
		try {

			Map<String, String> params = new HashMap<>(12);
			params.put("token", token);
			if (StrUtil.isNotEmpty(tarType)) {
				params.put("tartype", tarType);
			}

			IJPayHttpResponse result = WxPayApi.v3(
				RequestMethodEnum.GET,
				WxDomainEnum.CHINA.toString(),
				BasePayApiEnum.BILL_DOWNLOAD.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				params
			);
			log.info("result:{}", result);
			return JSONUtil.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping("/refund")
	@ResponseBody
	public String refund(@RequestParam(required = false) String transactionId,
						 @RequestParam(required = false) String outTradeNo) {
		try {
			String outRefundNo = PayKit.generateStr();
			log.info("??????????????????: {}", outRefundNo);

			List<RefundGoodsDetail> list = new ArrayList<>();
			RefundGoodsDetail refundGoodsDetail = new RefundGoodsDetail()
				.setMerchant_goods_id("123")
				.setGoods_name("IJPay ??????")
				.setUnit_price(1)
				.setRefund_amount(1)
				.setRefund_quantity(1);
			list.add(refundGoodsDetail);

			RefundModel refundModel = new RefundModel()
				.setOut_refund_no(outRefundNo)
				.setReason("IJPay ????????????")
				.setNotify_url(wxPayV3Bean.getDomain().concat("/v3/refundNotify"))
				.setAmount(new RefundAmount().setRefund(1).setTotal(1).setCurrency("CNY"))
				.setGoods_detail(list);

			if (StrUtil.isNotEmpty(transactionId)) {
				refundModel.setTransaction_id(transactionId);
			}
			if (StrUtil.isNotEmpty(outTradeNo)) {
				refundModel.setOut_trade_no(outTradeNo);
			}
			log.info("???????????? {}", JSONUtil.toJsonStr(refundModel));
			IJPayHttpResponse response = WxPayApi.v3(
				RequestMethodEnum.POST,
				WxDomainEnum.CHINA.toString(),
				BasePayApiEnum.REFUND.toString(),
				wxPayV3Bean.getMchId(),
				getSerialNumber(),
				null,
				wxPayV3Bean.getKeyPath(),
				JSONUtil.toJsonStr(refundModel)
			);
			// ???????????????????????????????????????????????????????????????
			boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
			log.info("verifySignature: {}", verifySignature);
			log.info("???????????? {}", response);

			if (verifySignature) {
				return response.getBody();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return null;
	}

	@RequestMapping(value = "/payNotify", method = {org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
	@ResponseBody
	public void payNotify(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<>(12);
		try {
			String timestamp = request.getHeader("Wechatpay-Timestamp");
			String nonce = request.getHeader("Wechatpay-Nonce");
			String serialNo = request.getHeader("Wechatpay-Serial");
			String signature = request.getHeader("Wechatpay-Signature");

			log.info("timestamp:{} nonce:{} serialNo:{} signature:{}", timestamp, nonce, serialNo, signature);
			String result = HttpKit.readData(request);
			log.info("?????????????????? {}", result);

			// ???????????????????????????????????????????????????verifyNotify ??????????????????????????????
			String plainText = WxPayKit.verifyNotify(serialNo, result, signature, nonce, timestamp,
				wxPayV3Bean.getApiKey3(), wxPayV3Bean.getPlatformCertPath());

			log.info("?????????????????? {}", plainText);

			if (StrUtil.isNotEmpty(plainText)) {
				response.setStatus(200);
				map.put("code", "SUCCESS");
				map.put("message", "SUCCESS");
			} else {
				response.setStatus(500);
				map.put("code", "ERROR");
				map.put("message", "????????????");
			}
			response.setHeader("Content-type", ContentType.JSON.toString());
			response.getOutputStream().write(JSONUtil.toJsonStr(map).getBytes(StandardCharsets.UTF_8));
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
