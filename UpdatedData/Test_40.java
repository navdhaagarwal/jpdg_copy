package UpdatedData;

import java.util.List;

public class Test_40 extends SolverAbst {
	SolverC(List<String> lines) {
		super(lines);
	}

	static class ResultBean {
		long max;
		long min;
		
		ResultBean(long max, long min) {
			this.max = max;
			this.min = min;
		}
		
		@Override
		public String toString() {
			return max + " " + min;
		}
	}
	@Override
	void solve(int caseNumber) {
		String[] parts = read().split(" ");
		long roomCount = Long.valueOf(parts[0]);
		long target = Long.valueOf(parts[1]);

		ResultBean bean = calc(roomCount, target);
		System.out.println(caseNumber + ": " + bean);
		makeAnswer(caseNumber, bean.max + " " + bean.min);
	}
	private ResultBean calc(long roomCount, long target) {
		if (target == 1)
			return new ResultBean(roomCount / 2, (roomCount - 1) / 2);

		boolean evenTarget = target % 2 == 0;
		target /= 2;
		if (evenTarget)
			roomCount = Math.max(roomCount / 2, (roomCount - 1) / 2);// larger
		else
			roomCount = Math.min(roomCount / 2, (roomCount - 1) / 2);// smaller
		return calc(roomCount, target);
	}
}


