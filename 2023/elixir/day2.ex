

defmodule DayTwo do
  @input_path "../resources/day2.txt"

  def get_input() do
    @input_path
    |> File.read!()
    |> (&Regex.replace(~r"([:;,])", &1, "")).()
    |> String.split("\n")
    |> Enum.map(&String.split/1)
  end

  def part_one(data) do
    data
    |> Enum.map(fn x ->
      x
      |> Enum.chunk_every(2)
      |> map_game()
      |> is_possible(%{"red" => 12, "green" => 13, "blue" =>14})
    end)
    |> Enum.filter(fn [_,y] -> y end)
    |> Enum.map(fn [i,_] -> i end)
    |> Enum.sum()
  end

  defp is_possible(val, compare) do
    [hd,tl] = val
    Enum.map(tl, fn {k,v} -> compare[k] >= v end)
    |> List.foldl(true, fn x, acc -> acc and x end)
    |> then(fn x -> [hd, x] end)
  end

  defp map_game([hd|tl]) do
    [_,j] = hd
    [String.to_integer(j), map_game(tl, %{})]
  end

  defp map_game([], cache), do: cache
  defp map_game([hd|tl], cache) do
    [v,k] = hd
    i = String.to_integer(v)
    case cache[k] do
      nil -> map_game(tl, Map.put(cache, k, i))
      x when x < i -> map_game(tl, Map.put(cache, k, i))
      _ -> map_game(tl, cache)
    end

  end

  def part_two(data) do
    data
    |> Enum.map(fn x ->
      x
      |> Enum.chunk_every(2)
      |> map_game()
      |> then(fn [_, y] -> y end)
      |> Enum.map(fn {_, y} -> y end)
      |> then(fn [hd | tl] ->
        List.foldl(tl, hd, fn x, acc -> acc * x end)
      end)
    end)
    |> Enum.sum()
  end

  def run() do
    input = get_input()

    IO.puts(part_one(input))
    IO.puts(part_two(input))
  end
end


DayTwo.run()
