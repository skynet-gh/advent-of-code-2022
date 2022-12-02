Rps :: enum u8 {
  ROCK :: 1;
  PAPER :: 2;
  SCISSORS :: 3;
}

score :: (them : Rps, response : Rps) -> int {
  us := cast(int) response % type_info(Rps).values.count;

  they : int = cast(int) them;
  outcome : int;
  if them == response {
    outcome = 3;
  } else if us == they - 1 {
    outcome = 0;
  } else {
    outcome = 6;
  };

  return outcome + cast(int) response;
}

main :: () {
  input := read_entire_file("../input/day02.txt");

  lines := split(input, "\n");

  part1 := 0;
  part2 := 0;

  for lines {
    if it == "" then continue;

    parts := split(it, " ");

    them : Rps;
    if parts[0] == {
      case "A"; them = .ROCK;
      case "B"; them = .PAPER;
      case "C"; them = .SCISSORS;
    };
    response1 : Rps;
    if parts[1] == {
      case "X"; response1 = .ROCK;
      case "Y"; response1 = .PAPER;
      case "Z"; response1 = .SCISSORS;
    };
    response2 : Rps;
    if parts[1] == {
      case "X"; response2 = them - 1;
      case "Y"; response2 = them;
      case "Z"; response2 = them + 1;
    };
    response2 = response2 % 3;
    if response2 == 0  then  response2 = .SCISSORS;

    part1 += score(them, response1);
    part2 += score(them, response2);
  }

  print("Part 1: %\n", part1);
  print("Part 2: %\n", part2);
}

#import "Basic";
#import "File";
#import "String";
