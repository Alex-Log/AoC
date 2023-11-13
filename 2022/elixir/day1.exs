defmodule DayOne do
  @input_path "../resources/day1sample.txt"

  def get_input(path) do
    path
    |> File.read!()
    |> String.split("\n\n")
    |> Enum.map(fn cal_groups ->
      cal_groups
      |> String.split("\n")
      |> Enum.map(&String.to_integer/1)
    end)
  end



  def part_one(data) do
    data
    |> Enum.map(&Enum.sum/1)
    |> Enum.max()
  end

  def part_two(data) do
    data
    |> Enum.map(&Enum.sum/1)
    |> Enum.sort()
    |> Enum.take(-3)
    |> Enum.sum()
  end

  def run do
    data = get_input(@input_path)

    #IO.puts(data)
    IO.puts("part one: #{part_one(data)}")
    IO.puts("part two: #{part_two(data)}")
  end

end

DayOne.run()
