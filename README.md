# ABusyList
# Pre-work - *A Busy List*

**A Busy List** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Lauren Wszolek**

Time spent: **3** hours building the first version following the tutorials, **10+** on the second

## User Stories

The following **required** functionality is completed:

* [x] User can **successfully add and remove items** from the todo list
* [x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [x] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [x] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file (cupboard, anyway.)
* [x] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [ ] Tweak the style improving the UI / UX, play with colors, images or backgrounds

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://i.giphy.com/l0O9zMcLxJ9MBvqHm.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

The greatest challenge I faced during the building of this app was my unfamiliarity with Java, and therefore with its
associated quirks. When was the last time I saw a private method (and "private" meant something)? Ha! I made the first app easily enough, and decided to scrap it and start over to see what I remembered and how many additional features I could add while doing this. What ended up taking the most time, many, many hours, and why this submission is so late (and not pretty), is when I moved over deleting list items to the edit view because I didn't like the long vs short tap (too easy to edit when you wanted to delete, and vice versa), I mistyped the declaration for the position as Integer and not int. In my mind, these terms have always been interchangable, and I assumed that int was an alias, since the other data types I had seen were all caps (Long, String). 

However, it worked for setting, meaning there must exist some type coercion for the set method on ArrayList, but not on remove. Remove has two versions, one where you pass the index of the object to remove, and the other where you pass the object you want removed, and if the "index" variable is Integer, Java will try to remove an "Integer" from the ArrayList. If you attempt to do a set, it will cast Integer into int.

As such, I had to descope making priorities editable, seting due dates, and getting checkboxes to function. Checkboxes were meant to be completion markers and would style the "done" items by greying them out, and also you could show/hide the done items. 

Checkboxes were another interesting bit. I was not able to get click events to fire when I put the checkboxes on the
list items, because the checkbox would eat all of those events. The fix was deceptively simple, but I'm not sure
I fully understand why it worked when other things did not.

All in all, there were many hours where I felt like I was really struggling, offset by feeling like a complete genius when I got something cool to work, like database persistence. While my app functions (mostly...), I'm not confident of the code quality, and I'm ever so slightly nervous that I didn't write any tests and wouldn't know where to start.

But it was excellent fun :) I'm going to go ahead and finish the other features in another branch just to play with
the app some more.

## License

    Copyright [2015] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
