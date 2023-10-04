package sdf;

import java.io.BufferedReader;
import java.io.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1786_정준원 {

	static String str = "";
	static int res = 0;
	static ArrayList<Integer> list;

	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		String t, p;
		int tmp = 0;

		t = br.readLine();
		p = br.readLine();

		list = new ArrayList<Integer>();

		kmp(t, p);

		System.out.println(res);

		for (int i = 0; i < res; i++)
			System.out.println(list.get(i));

	}

	public static int[] gettable(String ptn) {

		int[] pi = new int[ptn.length()];
		int j = 0;
		for (int i = 1; i < ptn.length(); i++) {
			while (j > 0 && ptn.charAt(i) != ptn.charAt(j)) {
				// 이 while문은 사실상 밑에 if 문에서 i4j0 과 같은경우 j 가 증가하고 나서 비로소 첫번째
				// 조건 만족.
				// System.out.println(" while" + "i" + i + "j" + j);

				j = pi[j - 1];
			}
			if (ptn.charAt(i) == ptn.charAt(j)) {
				// System.out.println("==" + "i" + i + "j" + j);
				pi[i] = ++j;
			}
		}

//		for (int i = 0; i < pi.length; i++)
		// System.out.println("ii" + pi[i]);

		return pi;
	}

	public static void kmp(String o, String ptn) {
		int pi[] = gettable(ptn);

		int j = 0;
		// System.out.println("o " + o);

		for (int i = 0; i < o.length(); i++) {

			while (j > 0 && o.charAt(i) != ptn.charAt(j)) {
				j = pi[j - 1];
				System.out.println("j" + j + "pi[j-1]" + pi[j - 1]);
			}

			if (o.charAt(i) == ptn.charAt(j)) {
				if (ptn.length() - 1 == j) {
					res++;

					list.add(i - pi.length + 2);
					j = pi[j];
				} else
					j++;
			}

		}

	}

}
