package closeset;
import java.util.*;

public class Closeset {
    
    private static int distance(Points p1, Points p2) {
        int dx = p1.x - p2.x;
        int dy = p1.y - p2.y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
    
    private static int distanceSquared(Points p1, Points p2) {
        int dx = p1.x - p2.x;
        int dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }
    
    public static Result CloseSet(ArrayList<Points> S, int left, int right) {
        int n = right - left + 1;
        
        // if (n ≤ 3) return (2 또는 3개의 점들 사이의 최근접 쌍)
        if (n <= 3) {
            return bruteForce(S, left, right);
        }
        
        // 정렬된 S를 같은 크기의 S_L과 S_R로 분할
        int mid = (left + right) / 2;
        Points midPoint = S.get(mid);
        
        // S_L에서의 최근접 점의 쌍
        Result CPL = CloseSet(S, left, mid);
        // S_R에서의 최근접 점의 쌍
        Result CPR = CloseSet(S, mid + 1, right);
        
        // 중간 영역에 속하는 점들 중에서 최근접 점의 쌍을 찾기
        
        // 두 결과 중 더 작은 거리를 가진 결과 선택
        Result minResult = (CPL.distance < CPR.distance) ? CPL : CPR;
        int d = minResult.distance;
        
        // 중간 영역에 속하는 점들 추출
        // 중간 영역 = midPoint의 x-좌표에서 ±d 범위
        ArrayList<Points> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int dx = Math.abs(S.get(i).x - midPoint.x);
            if (dx * dx < d) {
                strip.add(S.get(i));
            }
        }
        
        // 중간 영역에서 최근접 점의 쌍 찾기 
        Result CPC = stripClosest(strip, d);
        
        // CP_L, CP_C, CP_R 중에서 거리가 가장 짧은 쌍
        Result result = minResult;
        if (CPC != null && CPC.distance < result.distance) {
            result = CPC;
        }
        
        return result;
    }
    
    private static Result bruteForce(ArrayList<Points> points, int left, int right) {
        int minDist = Integer.MAX_VALUE;
        Points p1 = null, p2 = null;
        
        // 모든 점 쌍에 대해 거리 계산
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                int dist = distanceSquared(points.get(i), points.get(j));
                if (dist < minDist) {
                    minDist = dist;
                    p1 = points.get(i);
                    p2 = points.get(j);
                }
            }
        }
        
        return new Result(minDist, p1, p2);
    }
 
    private static Result stripClosest(ArrayList<Points> strip, int d) {
        if (strip.size() < 2) {
            return null;
        }
        
        // y 좌표 기준으로 정렬
        strip.sort((a, b) -> Integer.compare(a.y, b.y));
        
        int minDist = d;
        Points p1 = null, p2 = null;
        
        // 각 점에 대해 y-좌표 차이가 d 이내인 인접한 점들만 검사
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size(); j++) {
                int dy = strip.get(j).y - strip.get(i).y;
                // y-좌표 차이가 d 이상이면 더 이상 검사 X
                if (dy * dy >= minDist) {
                    break;
                }
                
                int dist = distanceSquared(strip.get(i), strip.get(j));
                if (dist < minDist) {
                    minDist = dist;
                    p1 = strip.get(i);
                    p2 = strip.get(j);
                }
            }
        }
        
        if (p1 == null || p2 == null) {
            return null;
        }
        
        return new Result(minDist, p1, p2);
    }
    
    public static void main(String[] args) {
        int N = 30;
        ArrayList<Points> arrList = new ArrayList<>();
        Random rand = new Random();
        
        // 임의의 점 30개 생성 (0~100 범위)
        for (int i = 0; i < N; i++) {
            int x = rand.nextInt(101);
            int y = rand.nextInt(101);
            arrList.add(new Points(x, y));
        }
        
        // x 좌표 기준으로 정렬
        arrList.sort((a, b) -> Integer.compare(a.x, b.x));
        
        // 생성된 점들 출력
        for (int i = 0; i < N; i++) {
            if (i % 5 == 0)
                System.out.println();
            System.out.print("(" + arrList.get(i).x + "," + arrList.get(i).y + ") ");
        }
        System.out.println();
        
        // CloseSet 알고리즘 실행
        Result result = CloseSet(arrList, 0, N - 1);
        
        // 실제 거리 계산
        int actualDistance = (int) Math.sqrt(result.distance);
        
        // 결과 출력
        System.out.println("가장 짧은 거리: " + actualDistance);
        System.out.println("가장 짧은 점 쌍: (" + result.p1.x + "," + result.p1.y +
                ")와 (" + result.p2.x + "," + result.p2.y + ")");
    }
}