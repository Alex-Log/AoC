defmodule DayOne do
  @input_path "../resources/day1.txt"

  def get_input do
    @input_path
    |> File.read!()
    |> String.split("\n")
  end

  def get_number(data) do
    case String.length(data) do
      2 -> data
      1 -> data <> data
      _ -> String.first(data) <> String.last(data)
    end
  end

  def part_one(data) do
    data
    |> Enum.map(fn line ->
      Regex.replace(~r"[^0-9.]", line, "")
      |> get_number()
      |> String.to_integer()
    end)
    |> Enum.sum()
  end

  def brute_force_replace_text_to_number(data) do
    txt_list = ["one","two", "three", "four", "five", "six", "seven", "eight", "nine"]
    num_list = ["1","2","3","4","5","6","7","8","9"]

    text_to_num(txt_list, num_list, data)
  end

  defp text_to_num([],[], data), do: data
  defp text_to_num([txt_hd | txt_tl], [num_hd | num_tl], data) do
    text_to_num(txt_tl, num_tl, Regex.replace(Regex.compile!(txt_hd), data, txt_hd <> num_hd <> txt_hd))
  end

  def part_two(data) do
    data
    |> Enum.map(fn line ->
      brute_force_replace_text_to_number(line)
    end)
    |> part_one()
  end

  def run do
    data = get_input()
    IO.puts("part one: #{part_one(data)}")
    IO.puts("part two: #{part_two(data)}")
  end
end

DayOne.run()
