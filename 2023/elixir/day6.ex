defmodule DaySix do
  @input "../resources/day6.txt"

  def get_input() do
    @input
    |> File.read!()
    |> String.split("\n")
    |> Enum.map(fn x ->
      x
      |> String.split()
      |> then(fn [_|tl] -> tl end)
      |> Enum.map(&String.to_integer/1)
    end)
  end

  def number_of_ways(time, dist) do
    Enum.map(0..time, fn x -> abs(time - x) * (time - abs(time - x)) end)
    |> Enum.filter(fn y -> y > dist end)
    |> length()
  end

  def part_one(data) do
    data
    |> then(fn [i,j] -> Enum.zip(i,j) end)
    |> Enum.map(fn {i,j} ->
      number_of_ways(i,j)
    end)
    |> then(fn [hd | tl] ->
      Enum.reduce(tl, hd, fn x, acc -> acc * x end)
    end)
  end

  def part_two(data) do
    data
    |> Enum.map(fn line ->
      Enum.reduce(line, "", fn x, acc -> acc <> Integer.to_string(x) end)
      |> String.to_integer()
    end)
    |> then(fn [i,j] -> number_of_ways(i, j) end)
  end

  def run() do
    data = get_input()

    IO.puts(part_one(data))
    IO.puts(part_two(data))
  end
end

DaySix.run()
