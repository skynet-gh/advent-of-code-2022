priority :: (c : u8) -> u8 {
  if c <= 90  then  return  c - 38;
  return c - 96;
}

inc :: (t : *Table, i : u8) {
  v, success := table_find(t, i);
  if  ! success  then  v = 0;
  table_set(t, i, v+1);
}

main :: () {
  input := read_entire_file("../input/day03.txt");

  lines := split(input, "\n");

  part1 := 0;
  for lines {
    if it == "" then continue;

    rucksack := it;
    half := rucksack.count / 2;

    compartment1 : Table(u8, int);
    compartment2 : Table(u8, int);

    for 0..half-1 {
      inc(*compartment1, rucksack[it]);
      inc(*compartment2, rucksack[it +  half]);
    }

    for compartment1 {
      k, _v := it_index, it;

      _k, in_c2 := table_find(*compartment2, k);

      if  in_c2  then  part1 += priority(k);
    }
  }

  third := lines.count / 3;
  part2 := 0;
  for 0..third-1 {
    rucksack1 : Table(u8, int);
    rucksack2 : Table(u8, int);
    rucksack3 : Table(u8, int);

    i := it * 3;
    for 0..lines[i].count-1 {
      inc(*rucksack1, lines[i][it]);
    }
    for 0..lines[i+1].count-1 {
      inc(*rucksack2, lines[i+1][it]);
    }
    for 0..lines[i+2].count-1 {
      inc(*rucksack3, lines[i+2][it]);
    }

    for rucksack1 {
      k, _v := it_index, it;

      _k2, in_c2 := table_find(*rucksack2, k);
      _k3, in_c3 := table_find(*rucksack3, k);

      if  in_c2 && in_c3  then  part2 += priority(k);
    }
  }

  print("Part 1: %\n", part1);
  print("Part 2: %\n", part2);
}

#import "Basic";
#import "File";
#import "Hash_Table";
#import "String";
