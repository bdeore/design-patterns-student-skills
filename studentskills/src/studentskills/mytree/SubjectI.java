package studentskills.mytree;

public interface SubjectI {

  void registerObserver(ObserverI node);

  void removeObserver(ObserverI node);

  void notifyObservers(String origValue, String newValue);
}
