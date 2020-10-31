Zmiany wersji pobranej z repozytorium:

1. Nadpisanie metod equals(Object obj) i hashCode() klasy Node, które są potrzebne przy porównywaniu węzłów
w testach.

2. Dodanie metody getRoot() w klasie RedBlackTree, która zrwaca aktualny korzeń drzewa. Jest ona wykorzystywana
w metodach testujących.

3. W metodzie putOnTheRight(Node<K, V> node, K key, V value) zmiana put(node.getRight(), key, value) na
node.setRight(put(node.getRight(), key, value)) (analogicznie dla putOnTheLeft(Node<K, V> node, K key, V value)).
Wcześniej nowe wartości nie stawały się liśćmi znajdujących się już w drzewie.

4. Dodanie zwracanej wartości do metody reorganizeTree(Node<K, V> node) (wcześniej void). Po zmianie metoda
zwraca zmodyfikowany węzeł, którym jest zastępowany jego poprzedni stan.