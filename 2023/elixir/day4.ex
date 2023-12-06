defmodule DayFour do
  @input_path "../resources/day4.txt"

  def get_input do
    @input_path
    |> File.read!()
    |> String.split("\n")
    |> Enum.map(fn x ->
      x
      |> String.split(": ")
      |> List.last()
      |> String.split(" | ")
      |> Enum.map(&String.split/1)
    end)
  end

  def part_one(data) do
    data
    |> Enum.map(fn card ->
      card
      |> find_match()
      |> get_exp()
    end)
    |> Enum.sum()
  end

  defp find_match([hd, tl]) do
    MapSet.intersection(MapSet.new(hd), MapSet.new(tl))
    |> MapSet.size()
  end

  defp get_exp(val) do
    if val == 0 do
      0
    else
      Integer.pow(2,val-1)
    end
  end

  def part_two(data) do
    data
    |> Enum.map(fn card ->
      card
      |> find_match()
    end)
    |> Enum.with_index(1)
    |> get_copies()
    |> List.flatten()
    |> length()
  end

  defp get_copies(data), do: Enum.map(data, fn x -> get_copies(x, data) end)

  defp get_copies(val, data) do
    {i,j} = val
    if i == 0 do
      val
    else
      [val] ++ Enum.map(Enum.slice(data,j,i), fn x -> get_copies(x, data) end)
    end
  end

  def run() do
    data = get_input()

    IO.puts("part one: #{part_one(data)}")
    IO.puts("part two: #{part_two(data)}")
  end

end

DayFour.run()
