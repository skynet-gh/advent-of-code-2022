main :: () {
  input := read_entire_file("../input/day01.txt");

  sections := split(input, "\n\n");

  elves: [..] int;

  for sections {
    foods := split(it, "\n");

    total := 0;
    for foods {
      total += string_to_int(it);
    }

    array_add(*elves, total);
  }

  sorted := quick_sort(elves, x => x);

  part1 := sorted[sorted.count - 1];
  part2 := part1 + sorted[sorted.count - 2] + sorted[sorted.count - 3];
  print("Part 1: %\n", part1);
  print("Part 2: %\n", part2);
}

#import "Basic";
#import "File";
#import "String";
#import "Sort";
