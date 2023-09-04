import Data.List (intersect, splitAt)
import Data.Char (ord)

getValue :: Char -> Int
getValue c
        | c >= 'a' = ord c - ord 'a' + 1
        | otherwise = (ord c - ord 'A') + 27

splitInHalf :: [Char] -> ([Char],[Char])
splitInHalf list = splitAt (length list `div` 2) list

commonItemValue :: [Char] -> Int
commonItemValue list = getValue $ head $ intersect x y
        where
                (x,y) = splitInHalf list

chunk :: Int -> [a] -> [[a]]
chunk n = takeWhile (not.null) . map (take n) . iterate (drop n)

dup3Value :: [[Char]] -> Int
dup3Value ls = getValue $ head $ intersect x $ intersect y z
        where
                (x : y : z : xs) = ls

main :: IO ()
main = do
        input <- lines <$> readFile "../data/day3.txt"
        print $ sum $ map commonItemValue input
        print $ sum $ map dup3Value $ chunk 3 input
        
    