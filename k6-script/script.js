import http from 'k6/http';
import { check, sleep } from 'k6';

// 초기 구매 가능 수량을 설정
let availCount = 100; // 예시로 100으로 설정

export const options = {
    vus: 30,  // 동시 사용자 수 10명
    duration: '4s',  // 30초 동안 테스트 실행
};

export default function () {
    const ticketId = 7;

    if (availCount > 0) {
        const url = `http://localhost:8080/purchase/${ticketId}/${availCount}`;
        const res = http.post(url);

        const success = check(res, {
            'status was 200': (r) => r.status === 200,
        });

        if (success) {
            availCount -= 1;
            console.log(`구매 성공: 남은 티켓 수량 = ${availCount}`);
        } else {
            console.log('구매 실패');
        }
    } else {
        console.log('티켓이 매진되었습니다.');
    }

    sleep(0.1);  // 1초 대기
}
