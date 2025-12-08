package advent_of_code.y2024;

import advent_of_code.Day;

import java.util.*;

public class  Day02 extends Day {
    public Day02(int year, int day, boolean runTests) {
        super(year, day, runTests, 2, 4);
    }

    @Override
    public String part1(Scanner scanner) {
        int safeReports = 0;
        while (scanner.hasNextLine()) {
            int[] report = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            String increasing = "null";
            boolean valid = true;
            for (int i = 0; i < report.length - 1; i++) {
                if (report[i] == report[i + 1]) valid = false;
                else if (report[i] > report[i + 1]) {
                    if (increasing.equals("null")) increasing = "false";
                    if (increasing.equals("true")) valid = false;
                    if (report[i] - report[i + 1] > 3) valid = false;
                } else {
                    if (increasing.equals("null")) increasing = "true";
                    if (increasing.equals("false")) valid = false;
                    if (report[i + 1] - report[i] > 3) valid = false;
                }
                if (!valid) break;
            }

            if (valid) safeReports++;
        }

        return String.valueOf(safeReports);
    }

    public int valid(ArrayList<Integer> report) {
        String increasing = "null";
        boolean valid = true;
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i) == report.get(i + 1)) valid = false;
            else if (report.get(i) > report.get(i + 1)) {
                if (increasing.equals("null")) increasing = "false";
                if (increasing.equals("true")) valid = false;
                if (report.get(i) - report.get(i + 1) > 3) valid = false;
            } else {
                if (increasing.equals("null")) increasing = "true";
                if (increasing.equals("false")) valid = false;
                if (report.get(i + 1) - report.get(i) > 3) valid = false;
            }
            if (!valid) return i;
        }

        return -1;
    }

    @Override
    public String part2(Scanner scanner) {
        int safeReports = 0;
        while (scanner.hasNextLine()) {
            String[] report = scanner.nextLine().split(" ");
            ArrayList<Integer> reportList = new ArrayList<>();
            for (String item : report) reportList.add(Integer.parseInt(item));
            int validInt = valid(reportList);
            if (validInt == -1) safeReports++;
            else {
                boolean validBool = false;
                for (int i = 0; i < reportList.size(); i++) {
                    ArrayList<Integer> newReportList = (ArrayList<Integer>) reportList.clone();
                    newReportList.remove(i);
                    if(valid(newReportList) == -1) {
                        validBool = true;
                        break;
                    }
                }
                if (validBool) safeReports++;
            }
        }
        return String.valueOf(safeReports);
    }
}
