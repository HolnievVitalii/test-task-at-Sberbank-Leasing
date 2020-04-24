1. Write a program to sort the array using the bubble method

2. There is a line consisting of words. All words in it are separated by a single space. You need to convert the string to a data structure that groups words by the first letter in the word. Then print only groups containing more than one element.
Groups must be sorted alphabetically. Words within a group must be sorted in descending order of characters; if the number of characters is equal, then sort in alphabetical order.

Example line: “boot shed watermelon bolt boxing exchange”
Sorted string: [b = [exchange, boxing, bolt], c = [boot, shed]]

3. Console utility for downloading files via HTTP
Accepts: a list of links in a text file
Action: download these files and put them in the specified folder on the local drive.
It should be able to download several files at the same time (in several streams, for example, 3 streams) and withstand the specified limit on the download speed, for example, 500 kilobytes per second.
