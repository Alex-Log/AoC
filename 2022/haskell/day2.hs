import Data.List
import Data.List.Split

main :: IO ()
main = do
    input <- lines <$> readFile "../data/day2.txt"
    --let test = map (splitOn " ") input
    print input