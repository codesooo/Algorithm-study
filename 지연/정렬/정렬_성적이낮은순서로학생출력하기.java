package 지연.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.180 성적이 낮은 순서로 학생 출력하기 / 난이도 하
 */
public class 정렬_성적이낮은순서로학생출력하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Student[] students = new Student[n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            students[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(students);

        for (Student student : students) {
            System.out.print(student.name + " ");
        }
    }

    static class Student implements Comparable<Student> {

        String name;
        int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(this.score, o.score);
        }
    }

}
