# CitySearch

1. This project is using a JSON file to list cities in alphabetical order with their longitude and latitude coordinates as a subtitle. On top of the app, there is a toolbar when click on it a search view is opened to type in the city needed. This is case insensitive so no matter how you type it will have results. The update of the list is done on every key input and is sorted by the alphabet.

2. The sorting of the list is implemented with Filterable interface that's linked on the adapter class, so there are two lists the one that is sent in the adapter oncreate and the filtered one that gives the results on the list with every key input change this is implemented in the Filtrable. For sorting of the list, the comparator is being used that implements [Timsort](https://en.wikipedia.org/wiki/Timsort)
  which is considered to be faster than [Trie](https://www.geeksforgeeks.org/trie-insert-and-search/)
 search that also could have been used in this case.

3. On startup, there is one screen that can go in both directions landscape and portrait mode. In landscape mode only one screen is being used so there is a list on the left side and map fragment on the right one. As you make a search and make click on the displayed cities you will be lead to the position on the map where you will see a pin on it.
For mapping of JSON to POJO and etc. [Gson](https://github.com/google/gson)
 library was used as it simplifies a lot of the process and the best way to go over 20k entries in the JSON file. For showing the results on screen 3rd party library of Recycleview was used instead of Listview for only reason cause the Recycleview implementation reuses cells while scrolling up/down, and in listview if you push that big of a list you will end up with application nor responding.

4. The app classes that were provided for testing were made in MVP structure so I've decided to make the two other activities in the same pattern.