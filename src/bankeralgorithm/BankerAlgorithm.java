package bankeralgorithm;


/**
 * �����м��㷨���д���ʵ�֣�ģ��̲�113ҳ���м��
 * 
 * �༶�����4��
 * ѧ�ţ�2014011725 
 * ������������ 
 * ���ʱ�䣺2016-4-13
 * 
 * @author dell-sun
 * @version 1.1.0
 * 
 */
 class BankerAlgorithm {
	// ������5����Դ3�࣬ģ��̲�113ҳ
	static final int n = 5; // ������
	static final int m = 3; // ��Դ����Ŀ

	// static int[] available = new int[m]; // ������Դ
	// static int[][] max = new int[n][m]; // ���������
	// static int[][] allocation = new int[n][m]; // �ѷ������Դ
	// static int[][] need = new int[n][m]; // �������
	// static int[] request = new int[m]; // ������

	static int[] available = { 3, 3, 2 }; // ������Դ
	static int[][] max = { { 7, 5, 3 }, 
						   { 3, 2, 2 }, 
						   { 9, 0, 2 }, 
						   { 2, 2, 2 }, 
						   { 4, 3, 3 } }; // ���������
	static int[][] allocation = { { 0, 1, 0 }, 
							      { 2, 0, 0 },
							      { 3, 0, 2 }, 
							      { 2, 1, 1 }, 
							      { 0, 0, 2 } };; // �ѷ������Դ
	static int[][] need = { { 7, 4, 3 }, 
							{ 1, 2, 2 }, 
							{ 6, 0, 0 }, 
							{ 0, 1, 1 }, 
							{ 4, 3, 1 } };; // �������
	static int[] request = new int[m]; // ������

	// ������ main����
	public static void main(String[] args) {
		// �ж�t0ʱ�̵İ�ȫ�� p113
		System.out.println("�ж�t0ʱ�̵İ�ȫ�ԣ�");
		if (securityAlgorithm()) {
			System.out.println("t0ʱ���ǰ�ȫ��");
		} else {
			System.out.println("t0ʱ���ǲ���ȫ��");
		}

		// p1������Դ
		System.out.println("/*************************************************************/");
		request[0] = 1;
		request[1] = 0;
		request[2] = 2;
		bankerAlgorithm(request, 1);

		// p4������Դ
		System.out.println("/*************************************************************/");
		request[0] = 3;
		request[1] = 3;
		request[2] = 0;
		bankerAlgorithm(request, 4);

		// p0������Դ
		System.out.println("/*************************************************************/");
		request[0] = 0;
		request[1] = 2;
		request[2] = 0;
		bankerAlgorithm(request, 0);

		// p0������Դ
		System.out.println("/*************************************************************/");
		System.out.println("������м��㷨�а�p0���������������Ϊrequest(0,1,0)");
		request[0] = 0;
		request[1] = 1;
		request[2] = 0;
		bankerAlgorithm(request, 0);
	}

	/**
	 * ���м��㷨���õ���ά����ӷ�twoMatrixAdd����ά�������twoMatrixSub������Ƚ�compare,
	 * ��ӡ��Ϊ����printOneMa,��������ʵ��
	 * 
	 * @param request[]
	 *            ��������
	 * @param i
	 *            ��������,�������1,2,3
	 * @return
	 */
	 public static void bankerAlgorithm(int request[], int i) {
		// ����1
		// ��ӡ��ǰ�����Ƚϵ�request��need����һЩ��Ϣ
		System.out.println("����" + i + "ִ���������м��㷨����");
		System.out.println("request");
		printOneMa(request);
		System.out.println("need" + i);
		printOneMa(need[i]);

		if (compare(request, need[i])) {
			System.out.println("request<=need");
			// ����2
			// ��ӡ��ǰ�����Ƚϵ�request��available����һЩ��Ϣ
			System.out.println("request");
			printOneMa(request);
			System.out.println("available");
			printOneMa(available);

			if (compare(request, available)) {
				System.out.println("request<=available");
				// ����3
				available = oneMatrixSub(available, request);
				allocation[i] = oneMatrixAdd(allocation[i], request);
				need[i] = oneMatrixSub(need[i], request);

				// ����4
				// ���ð�ȫ���㷨
				if (securityAlgorithm()) {
					System.out.println("�˴���Դ�����ϵͳ���ڰ�ȫ״̬���������󣬽���Դ�����" + i + "����");
					System.out.println("��ʱ��available��");
					printOneMa(available);
				} else {
					System.out.println("�˴���Դ�����ϵͳ���ڲ���ȫ״̬�����������󣬴˴η������ϣ���Դ��������" + i + "����");

					available = oneMatrixAdd(available, request);
					allocation[i] = oneMatrixSub(allocation[i], request);
					need[i] = oneMatrixAdd(need[i], request);

					System.out.println(i + "���̴��ڵȴ�״̬��");
					System.out.println("��ʱ��available��");
					printOneMa(available);
				}
			} else {
				System.out.println("request>available");
				System.out.println("�����㹻��Դ������" + i + "�ȴ� (wait)");
			}
		} else {
			System.out.println("request>need");
			System.out.println("����Ҫ��Դ�����Ѿ������˽���" + i + "���������ֵ��request>need��");
		}
	}

	/**
	 * ��ȫ���㷨���жϵ�ǰ���̷����ϵͳ�Ƿ��ڰ�ȫ״̬ �����ȫ������true������false
	 * 
	 * @return ��ȫ����true
	 */
	 public static boolean securityAlgorithm() {
		// ����1
		// ��ʼ��һЩ����
		// int[] work = available; ����ĸ�ֵ�����������Ļ�����������work���и�ֵ�������ı���availabe,
		// ���¶���һ��һά���飬��available��ֵ���������Ϊ����work
		System.out.println("-----��ȫ���㷨ִ�У�-----");
		int[] work = new int[m];

		for (int i = 0; i < work.length; i++) {
			work[i] = available[i];
		}

		boolean[] finish = new boolean[n];
		for (int i = 0; i < finish.length; i++) {
			finish[i] = false;
		}
		int count = 0; // �������������ж��Ƿ�finish[n]������Boolean����true

		// ����2
		for (int i = 0; i < n; i++) {
			if (compare(need[i], work) && finish[i] == false) // �ҵ�������������Ľ���i
			{
				// ����3
				System.out.print("����" + i + "�����Դ����˳��ִ�У�ֱ����ɣ����ͷ���Դ");
				work = oneMatrixAdd(work, allocation[i]);
				System.out.print("	 " + "Work+Allocation:");

				printOneMa(work);// ��ӡwork+allocation

				System.out.println();
				finish[i] = true;
				// go to step2
				// ��Ϊforѭ���ڵĲ���ִ�к󣬻���i++����������Ϊ�˱�֤i��0��ʼ������i=-1,i++ = 0;
				if (i == n - 1) {
					i = -1;
				}
			}
		}
		// ����4

		for (int j = 0; j < finish.length; j++) {
			if (finish[j] == true) {
				count++;
			}
		}

		return count == n;
	}

	/**
	 * дһ��һά����������������
	 * 
	 * @param first
	 *            һά����
	 * @param second
	 *            һά����
	 * @return һά����
	 */
	 public static int[] oneMatrixSub(int[] first, int[] second) {
		for (int i = 0; i < first.length; i++) {
			first[i] = first[i] - second[i];
		}
		return first;
	}

	/**
	 * дһ��һά�������ӷ�������
	 * 
	 * @param first
	 *            һά����
	 * @param second
	 *            һά����
	 * @return һά����
	 */
	 public static int[] oneMatrixAdd(int[] first, int[] second) {
		for (int i = 0; i < first.length; i++) {
			first[i] = first[i] + second[i];
		}
		return first;
	}

	/**
	 * дһ����ά�������ӷ�������
	 * 
	 * @param a
	 *            ��ά����
	 * @param b
	 *            ��ά����
	 * @return ��ά����
	 */
	 public int[][] twoMatrixAdd(int[][] a, int[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				a[i][j] = a[i][j] + b[i][j];
			}
		}
		return a;
	}

	/**
	 * дһ����ά����������������
	 * 
	 * @param a
	 *            ��ά����
	 * @param b
	 *            ��ά����
	 * @return ��ά����
	 */
	 public static int[][] twoMatrixSub(int[][] a, int[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				a[i][j] = a[i][j] - b[i][j];
			}
		}
		return a;
	}

	/**
	 * ����ȽϺ������ж���������ÿ����Ӧλ���ϵ����Ƿ񶼴�����һ������,�����һ��С�ڵ��ڵڶ�������true,����Ϊfalse
	 * 
	 * @param a
	 *            һά����
	 * @param b
	 *            һά����
	 * @return boolean
	 */
	 public static boolean compare(int[] first, int[] second) {
		int count = 0;
		for (int i = 0; i < first.length; i++) {
			if (first[i] <= second[i]) {
				count++;
			}
		}
		
		return count == first.length;
	}

	/**
	 * ��ӡһά��������
	 * 
	 * @param temp 
	 * 				һά����
	 */
	 public static void printOneMa(int[] temp) {
		for (int i = 0; i < temp.length; i++) {
			System.out.print(temp[i]);
			System.out.print("	");
		}
		System.out.println();
	}
	 
}
