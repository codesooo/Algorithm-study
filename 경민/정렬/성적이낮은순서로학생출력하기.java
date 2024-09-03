package 정렬;

import java.util.*;

public class 성적이낮은순서로학생출력하기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 학생 수 입력받기

        // Student 객체들을 저장할 리스트
        ArrayList<Student> studentList = new ArrayList<>();

        // 학생의 이름과 점수를 입력받아 리스트에 추가
        for (int i=0; i<n; i++) {
            String name = scanner.next();  // 학생 이름 입력
            int score = scanner.nextInt(); // 학생 점수 입력
            studentList.add(new Student(name, score)); // Student 객체를 생성해 리스트에 추가
        }

        // studentList를 Student 클래스의 compareTo 메서드 기준으로 정렬 (점수가 낮은 순으로)
        Collections.sort(studentList);

        // 정렬된 학생 이름 출력
        for (int i=0; i<n; i++){
            System.out.print(studentList.get(i).getName() + " ");
        }
    }

    // Student 클래스: 학생의 이름과 점수를 저장하고 비교할 수 있는 클래스
    private static class Student implements Comparable<Student> {
        String name;  // 학생 이름
        int score;    // 학생 점수

        // 생성자
        public Student(String name, int score){
            this.name = name;
            this.score = score;
        }

        // 학생 이름을 반환하는 메서드
        public String getName(){
            return this.name;
        }

        // 비교 메서드: 점수를 기준으로 비교 (점수가 낮은 순서로 정렬되도록 설정)
        @Override
        public int compareTo(Student o) {
            return Integer.compare(this.score, o.score);
        }
    }
}