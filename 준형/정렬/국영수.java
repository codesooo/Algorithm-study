package 정렬;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String name;
    int korean;
    int english;
    int math;

    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }
}

public class 국영수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Student> students = new ArrayList<>();

        // 학생 정보를 입력받아 리스트에 저장
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int korean = sc.nextInt();
            int english = sc.nextInt();
            int math = sc.nextInt();
            students.add(new Student(name, korean, english, math));
        }

        // 정렬 기준: 국어 내림차순, 영어 오름차순, 수학 내림차순, 이름 사전순
        students.sort((a, b) -> {
            if (a.korean != b.korean) {
                return b.korean - a.korean; // 국어 점수 내림차순
            } else if (a.english != b.english) {
                return a.english - b.english; // 영어 점수 오름차순
            } else if (a.math != b.math) {
                return b.math - a.math; // 수학 점수 내림차순
            } else {
                return a.name.compareTo(b.name); // 이름 사전순
            }
        });

        // 결과 출력
        for (Student student : students) {
            System.out.println(student.name);
        }

        sc.close();
    }
}
