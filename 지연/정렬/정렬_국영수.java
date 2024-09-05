package 지연.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.359 국영수 / 난이도 하
 * 백준 실버4 https://www.acmicpc.net/problem/10825
 */
public class 정렬_국영수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int korScore = Integer.parseInt(st.nextToken());
            int enScore = Integer.parseInt(st.nextToken());
            int mathScore = Integer.parseInt(st.nextToken());

            students.add(new Student(name, korScore, enScore, mathScore));
        }

        Collections.sort(students);

        for (int i = 0; i < n; i++) {
            System.out.println(students.get(i).getName());
        }
    }

    private static class Student implements Comparable<Student>{
        String name;
        int korScore;
        int engScore;
        int mathScore;

        public Student(String name, int korScore, int engScore, int mathScore) {
            this.name = name;
            this.korScore = korScore;
            this.engScore = engScore;
            this.mathScore = mathScore;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Student o) {
            /*
            1. 국어 점수가 감소하는 순서로
            2. 국어 점수가 같으면 영어 점수가 증가하는 순서로
            3. 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
            4. 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서
            */
            if (this.korScore == o.korScore && this.engScore == o.engScore && this.mathScore == o.mathScore) {
                return this.name.compareTo(o.name);
            }
            if (this.korScore == o.korScore && this.engScore == o.engScore) {
                return Integer.compare(o.mathScore, this.mathScore);
            }
            if (this.korScore == o.korScore) {
                return Integer.compare(this.engScore, o.engScore);
            }
            return Integer.compare(o.korScore, this.korScore);
        }
    }
}
