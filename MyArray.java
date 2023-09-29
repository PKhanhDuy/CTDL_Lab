package ctdl;

import java.sql.Time;
import java.util.Timer;

public class MyArray {
	private int[] arrays;

	public MyArray(int[] array) {
		this.arrays = array;
	}

// phương thức thêm phần tử vào mảng với các phần tử bị đảo ngược vị trí.
	public int[] mirror() {
		int[] resArray = new int[arrays.length * 2];
		for (int i = 0; i < arrays.length; i++) {
			resArray[i] = arrays[i];
			resArray[resArray.length - i - 1] = arrays[i];
		}
		return resArray;
	}

//phương thức xóa các phần tử bị trùng lập
	public int[] moveDuplicates() {
		int j = 0;
		int[] tempArr = new int[arrays.length];
		for (int i = 0; i < arrays.length; i++) {
			if (!contain(arrays[i], tempArr)) {
				tempArr[j] = arrays[i];
				j++;
			}
		}
		int[] resArr = new int[j];
		for (int i = 0; i < resArr.length; i++) {
			resArr[i] = tempArr[i];
		}
		return resArr;
	}
//kiểm tra xem mảng có chưa phần tử đó hay không
	public boolean contain(int num, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (num == array[i])
				return true;
		}
		return false;
	}
//lấy các phần tử bị bỏ lỡ trong mảng đã cho
	public int[] getMissingValues() {
		int[] res = new int[0];
		int tempNum;
		int j = 0;
		for (int i = 1; i < arrays.length; i++) {
			tempNum = arrays[i];
			if (tempNum != arrays[i - 1] + 1) {
				int count = arrays[i - 1] + 1;
				while (count < tempNum) {
					int[] newRes = upSize(res);
					newRes[j] = count;
					count++;
					j++;
					res = newRes;
				}
			}
		}
		return res;
	}

	public static int[] upSize(int[] array) {
		int[] newArr = new int[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			newArr[i] = array[i];
		}
		return newArr;
	}

	
//phương thức điền các vị trí bị thiếu bằng trung bình cộng nhỏ nhất của k hàng xóm.
	public int[] fillMissingValue(int k) {
		int[] resArr = arrays;
		int index = -1;
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i] == -1) {
				index = i;

				if (index == 0) {
					int minest = 0;
					for (int d = 1; d < k + 1; d++) {
						minest += arrays[d];
					}
					resArr[index] = (minest / k);
				}
				if (index == arrays.length - 1) {
					int minest = 0;
					for (int a = arrays.length - k - 1; a < arrays.length - 2; a++) {
						minest += arrays[a];
					}
					resArr[index] = minest;
				}
				int j = 0;
				if (index > (k / 2 + 1) && index < (arrays.length - (k / 2 + 1))) {
					if (k % 2 != 0) {
						int[] neiborArr = new int[k + 1];
						for (int b = index - (k / 2) - 1; b <= index + (k / 2) + 1; b++) {
							if (b != index) {
								neiborArr[j] = arrays[b];
								j++;
							}
						}
						int minest = minAverageIfOdd(neiborArr, k);
						resArr[index] = minest;
					} else {
						int[] neiborArr = new int[k];
						for (int c = index - (k / 2); c < index + (k / 2) + 1; c++) {
							if (c != index) {
								neiborArr[j] = arrays[c];
								j++;
							}
						}
						int minest = minAverageIfEven(neiborArr, k);
						resArr[index] = minest;
					}
				} else if (index <= k - 2 || index >= arrays.length - (k / 2) - 1) {
					int[] neiborArr = new int[k];
					if (index <= k - 2) {
						for (int t = 0; t < k + 1; t++) {
							if (t != index) {
								neiborArr[j] = arrays[t];
								j++;
							}
						}
					} else if (index >= arrays.length - (k / 2) - 1) {
						int q = k - 1;
						for (int n = arrays.length - 1; n >= arrays.length - k - 1; n--) {
							if (n != index) {
								neiborArr[q] = arrays[n];
								q--;
							}
						}
					}
					int minest = minAverageIfEven(neiborArr, k);
					resArr[index] = minest;
				}
			}
		}
		return resArr;
	}

	// phương thức tình giá trị trung bình nhỏ nhất đối với số hàng xóm là số lẻ
	public static int minAverageIfOdd(int[] array, int k) {
		int res = 0;
		int min = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = i; j < i + k; j++) {
				min += array[j];
			}
			min = min / k;
			if (res == 0) {
				res = min;
			} else {
				if (min < res)
					res = min;
			}
		}
		return res;
	}

//phương thức tình giá trị trung bình nhỏ nhất đối với số hàng xóm là số chẳn
	public static int minAverageIfEven(int[] array, int k) {
		int res = 0;
		for (int j = 0; j < k; j++) {
			res += array[j];
		}
		res = res / k;
		return res;
	}

//	public static int minAverage(int[] array, int k) {
//		int res = 0;
//		int min = 0;
//		if (k % 2 != 0) {
//			for (int i = 0; i < 2; i++) {
//				for (int j = i; j < i + k; j++) {
//					min += array[j];
//				}
//				if (res == 0) {
//					res = min;
//				} else {
//					if (min < res)
//						res = min;
//				}
//			}
//		} else {
//			for (int j = 0; j < k; j++) {
//				res += array[j];
//			}
//		}
//		return res;
//	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
	}

	public static void main(String[] args) {
		int[] array = { -1, 11, 12, -1, -1, 19, 20, -1 };
		MyArray myArray = new MyArray(array);
//		int[] temp = myArray.mirror();
//		int[] temp = myArray.moveDuplicates(););
//		int[] temp = myArray.getMissingValues();
		int[] temp = myArray.fillMissingValue(2);
		printArray(temp);

	}

}
