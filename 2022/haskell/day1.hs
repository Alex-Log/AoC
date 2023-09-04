import Data.List
import Data.List.Split
import Data.Ord

getSortedCalories :: [[Char]] -> [Int]
getSortedCalories input =
    sortDown $ map (sum . map read) chunks :: [Int]
    where
        chunks = splitOn [""] input
        sortDown = sortBy (comparing Down)

main :: IO ()
main = do
    input <- lines <$> readFile "../data/day1.txt"
    let calories = getSortedCalories input
    print $ "day1 pt1: " ++ show (head calories)
    print $ "day1 pt2: " ++ show (sum $ take 3 calories)