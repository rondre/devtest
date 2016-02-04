/**
 * created by Ron Smith 2/3/16
 */
package com.build.devtest;

import java.util.ArrayList;
import java.util.List;

public class ParentViewMapperImpl implements ParentViewMapper {

    public List<ParentView> mapRowsToViews(List<ParentRow> parentRows, List<ChildRow> childRows) {
        List<ParentView> parentViewList = new ArrayList<ParentView>();  //This will be return object

        // loop through parentRows to create parentList
        for (ParentRow parentRow : parentRows) {

            //Start building first ParentView object
            ParentView parentView = new ParentView(
                    parentRow.getFirstName(),
                    parentRow.getLastName(),
                    parentRow.getAge(),
                    parentRow.getParentId(),
                    new ArrayList<ChildView>());

            //Add parentView to the parentViewList object
            parentViewList.add(parentView);
        }

        //Loop through new parent view list to add child rows to build child view list if applicable for parent id
        for (ParentView parentView : parentViewList) {

            //Begin ChildView List to attach to parentViewList records individually
            List<ChildView> childViews = new ArrayList<ChildView>();

            //loop through all children by parent record - build ChildView list then attach to parent record
            for (ChildRow childRow : childRows) {

                //Compare the parent id's
                //NOTE: using ignore case as the test has parents setup differently case wise.
                //I'm not sure if that was intentional to use ignore case on the string comparison but it could be
                //argued that the cases could refer to different parents thus the test would fail
                //i don't think this was the intention but wanted to put that out there (
                if (childRow.getParentId().equalsIgnoreCase(parentView.getParentId())) {
                    childViews.add(new ChildView(childRow.getFirstName(), childRow.getLastName(), childRow.getAge(), childRow.getChildId(), parentView));
                }
            }

            //Set the ChildView list to the parentView object
            parentView.setChildViews(childViews);
        }

        return parentViewList;
    }
}
