package pl.wit.studentsdatabase.repository;

import java.util.List;

import pl.wit.studentsdatabase.model.Group;


public interface GroupDataAccess {
	
	List<Group> getGroups();
	
	Group getGroupByCode(String code);
	
	Group getGroupById(int id);
	
	List<Group> getGroupBySpecialisation(String kind);
	
	List<Group> getGroupsByDescription(String description);
	
	List<Group> getGroupsByStudentId(int studentId);
	
	void addGroup(Group newGroup);
	
	void addGroups(List<Group> newGropus);
	
	void deleteById(int id);
	
	void update(Group group);

}
