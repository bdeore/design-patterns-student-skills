package studentskills.mytree;

/** Interface for subjects */
public interface SubjectI {

  void registerObserver(ObserverI node);

  void removeObserver(ObserverI node);

  void notifyObservers(String origValue, String newValue);
}
