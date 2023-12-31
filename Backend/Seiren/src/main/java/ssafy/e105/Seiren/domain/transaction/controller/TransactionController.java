package ssafy.e105.Seiren.domain.transaction.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ssafy.e105.Seiren.domain.transaction.service.TransactionService;
import ssafy.e105.Seiren.global.utils.ApiResult;
import ssafy.e105.Seiren.global.utils.ApiUtils;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/transactions")
@Tag(name = "거래 API")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "거래 상품 목록 리스트")
    @GetMapping()
    public ApiResult getTransactionProductList(HttpServletRequest request,
            @RequestParam("page") int page) {
        return ApiUtils.success(transactionService.getTransactionProductList(request, page, 10));
    }

    @Operation(summary = "거래 상품 상세 정보")
    @GetMapping("/detail/{productid}")
    public ApiResult getTransactionProductDetail(
            HttpServletRequest request,
            @PathVariable("productid") Long productId) {
        return ApiUtils.success(transactionService.getTransactionProductDetail(request, productId));
    }

    @Operation(summary = "TTS 글자수 체크")
    @GetMapping("/check")
    public ApiResult checkRegistTTS(
            @RequestParam("transactionid") Long transactionId,
            @RequestParam("text") @Pattern(regexp = "[가-힣]{1,200}", message = "한글로 200글자 이하의 문자열만 가능합니다.") String text) {
        return ApiUtils.success(
                transactionService.checkRegistTTS(transactionId, text));
    }

    @Operation(summary = "TTS 등록")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResult registTTS(
            @RequestParam("transactionid") Long transactionId,
            @RequestParam("text") @Pattern(regexp = "[가-힣]{1,200}", message = "한글로 200글자 이하의 문자열만 가능합니다.") String text,
            @RequestPart MultipartFile file
    ) {
        return ApiUtils.success(
                transactionService.registTTS(transactionId, text, file));
    }

    @Operation(summary = "구매 상품 사용 기록 리스트")
    @GetMapping("/history")
    public ApiResult getTransactionProductHistroy(@RequestParam("transactionid") Long transactionId,
            @RequestParam("page") int page) {
        return ApiUtils.success(
                transactionService.getTransactionProductHistory(transactionId, page, 10));
    }

    @Operation(summary = "총 구매 목소리 갯수")
    @GetMapping("/totalcount")
    public ApiResult getTransactionTotal(HttpServletRequest request) {
        return ApiUtils.success(transactionService.getTransactionTotal(request));
    }

    @Operation(summary = "목소리 구매 목록 영수증")
    @GetMapping("/receipt")
    public ApiResult getTransactionProductReceipt(HttpServletRequest request,
            @RequestParam("page") int page) {
        return ApiUtils.success(transactionService.getTransactionProductReceipt(request, page, 10));
    }

    @Operation(summary = "총 결제 내역 갯수")
    @GetMapping("/description/totalcount")
    public ApiResult getTransactionDescriptions(HttpServletRequest request) {
        return ApiUtils.success(transactionService.getTransactionDescriptions(request));
    }

    @Operation(summary = "사용 가능 / 불가능 상품 개수")
    @GetMapping("/availability")
    public ApiResult getProductAvailability(HttpServletRequest request) {
        return ApiUtils.success(transactionService.getProductAvailability(request));
    }

    @Operation(summary = "구매한 목소리 남은 글자 수")
    @GetMapping("/remainLetters/{productid}")
    public ApiResult getRemainLetter(HttpServletRequest request, @PathVariable Long productid) {
        return ApiUtils.success(transactionService.getRemainLetters(request, productid));
    }

}
