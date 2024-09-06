package 정렬;

import java.util.*;

public class 국영수 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        ArrayList<Student> studentList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            String name = scanner.next();  // 학생 이름 입력
            int korean = scanner.nextInt(); // 국어 점수 입력
            int english = scanner.nextInt(); // 영어 점수 입력
            int math = scanner.nextInt(); // 수학 점수 입력
            studentList.add(new Student(name, korean, english, math)); // Student 객체를 생성해 리스트에 추가
        }

        Collections.sort(studentList);

        for (int i=0; i<N; i++){
            System.out.println(studentList.get(i).getName() + " ");
        }
    }

    private static class Student implements Comparable<Student> {
        String name; // 학생 이름
        int korean; // 국어 점수
        int english; // 영어 점수
        int math; // 수학 점수

        // 생성자
        public Student(String name, int korean, int english, int math){
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        // 학생 이름을 반환하는 메서드
        public String getName(){
            return this.name;
        }

        // 비교 메서드
        @Override
        public int compareTo(Student o) {
            // 국어, 영어, 수학 점수가 모두 동일할 경우, 이름을 사전순으로 비교
            if (this.korean == o.korean && this.english == o.english && this.math == o.math) {
                return this.name.compareTo(o.name);
            }
            // 국어와 영어 점수가 동일할 경우, 수학 점수를 비교 (내림차순으로 정렬)
            if (this.korean == o.korean && this.english == o.english) {
                return Integer.compare(o.math, this.math);
            }
            // 국어 점수가 동일할 경우, 영어 점수를 비교 (오름차순으로 정렬)
            if (this.korean == o.korean) {
                return Integer.compare(this.english, o.english);
            }
            // 모두 아닌 경우 국어 점수를 비교 (오름차순으로 정렬)
            return Integer.compare(o.korean, this.korean);
        }
    }
}
