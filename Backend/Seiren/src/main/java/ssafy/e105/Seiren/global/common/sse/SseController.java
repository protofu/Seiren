package ssafy.e105.Seiren.global.common.sse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class SseController {

    private static final long TIMEOUT = 60 * 60 * 1000L; // 1시간
    private final SseService sseService;

    @Operation(summary = "sse 연결 요청")
    @GetMapping(value = "/api/sse/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(@RequestParam Long userId, HttpServletResponse response) {
        response.addHeader("X-Accel-Buffering", "no"); // nginx 관련 설정

        SseEmitter sseEmitter = new SseEmitter(TIMEOUT);
        sseEmitter = sseService.subscribe(userId, sseEmitter);
        return sseEmitter;
    }

    @Operation(summary = "sse 연결 종료")
    @DeleteMapping("/api/sse/disconnect")
    public void disconnect(HttpServletRequest request) {
        sseService.disConnect(request);
    }

    @Operation(summary = "sse 연결 테스트")
    @GetMapping("/api/sse/test")
    public void test(HttpServletRequest request, @RequestParam NotificationType type,
            @RequestParam String str) {
        sseService.sendTest(request, type, str);
    }
}
