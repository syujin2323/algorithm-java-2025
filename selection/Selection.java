package selection;

public class Selection {
    
    public static int selection(int[] A, int left, int right, int k) {
        // 피봇과 A[left]의 자리를 바꾼 후, 
        // 피봇과 배열의 각 원소를 비교하여 피봇보다 작은 숫자는 A[left]~A[p-1]로 옮기고,
        // 피봇보다 큰 숫자는 A[p+1]~A[right]로 옮기며, 피봇은 A[p]에 놓는다.
        int p = partition(A, left, right);
        
        // S = Small group의 크기
        int S = (p - 1) - left + 1;

        // Small group에서 찾기
        if (k <= S) {
            return selection(A, left, p - 1, k);
        }
        // 피봇 = k번째 작은 숫자
        else if (k == S + 1) {
            return A[p];
        }
        // Large group에서 찾기
        else {
            return selection(A, p + 1, right, k - S - 1);
        }
    }
    
    private static int partition(int[] A, int left, int right) {
        // 피봇을 랜덤하게 선택 
        int pivot = A[right];
        int i = left - 1;
        
        // 피봇보다 작은 요소들을 왼쪽으로 이동
        for (int j = left; j < right; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
        }
        
        // 피봇을 올바른 위치(A[p])로 이동
        swap(A, i + 1, right);
        return i + 1; // 피봇의 위치 p 반환
    }
    
    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    public static void main(String[] args) {
        int A[] = {48, 12, 70, 38, 75, 67, 96, 52, 81};
        int k = 5;
        
        System.out.println(k + "번째로 작은 요소: " + selection(A, 0, A.length - 1, k));
    }
}