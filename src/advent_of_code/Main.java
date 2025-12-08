package advent_of_code;

import advent_of_code.y2015.Run2015;
import advent_of_code.y2022.Run2022;
import advent_of_code.y2023.Run2023;
import advent_of_code.y2024.Run2024;
import advent_of_code.y2025.Run2025;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
   public static void run(int year, int day, boolean runTests) {
      switch (year) {
         case 2015 -> Run2015.run(year, day, runTests);
         case 2022 -> Run2022.run(year, day, runTests);
         case 2023 -> Run2023.run(year, day, runTests);
         case 2024 -> Run2024.run(year, day, runTests);
         case 2025 -> Run2025.run(year, day, runTests);
         default -> System.out.println("Year not found");
      }
   }

   public static void main(String[] args) {
      String[] date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()).split("-");
      if (args.length == 0) run(Integer.parseInt(date[0]), Integer.parseInt(date[2]), true);
      else run(Integer.parseInt(args[0]), Integer.parseInt(args[1]), true);
   }
}