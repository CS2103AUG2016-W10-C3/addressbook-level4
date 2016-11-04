﻿# User Guide

## Table of Contents
1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Getting Started](#3-getting-started)
  1. [Requesting Help](#31-requesting-help)
  2. [Choosing Your Save Location](#32-choosing-your-save-location)
  3. [Loading Save Files](#33-loading-save-files)
  4. [Adding Tasks](#34-adding-tasks)
  5. [Editing Task Details](#35-editing-task-details)
  6. [Deleting Tasks](#36-deleting-tasks)
  7. [Marking Completed Tasks](#37-marking-completed-tasks)
  8. [Undoing and Redoing](#38-undoing-and-redoing)
  9. [Selecting Specific Tasks](#39-selecting-specific-tasks)
  10. [Finding Specific Tasks](#310-finding-specific-tasks)
  11. [Filtering the Task List](#311-filtering-the-task-list)
  12. [Locating a Destination](#312-locating-a-destination)
  13. [Clearing Saved Data](#313-clearing-saved-data)
  14. [Exiting SmartyDo](#314-exiting-smartydo)
4. [Smart Features](#4-smart-features)
  1. [FlexiCommand](#41-flexicommand)
  2. [Saving The Data](#42-saving-the-data)
5. [Summary](#5-summary)
  1. [Command Summary](#51-command-summary)
  2. [Keyboard Shortcuts](#52-keyboard-shortcuts)

<!-- /MarkdownTOC -->

## 1. Introduction
SmartyDo is a **to-do-list** application. With SmartyDo, forgetting upcoming deadlines and sleepless nights over incomplete tasks are a thing of the past. SmartyDo **increases your efficiency** by showing the lists of tasks that can be completed simultaneously. Treat SmartyDo like your personal assistant and just focus on **completing your tasks**!

## 2. Quick Start
**Launch SmartyDo**: Simply double-click on the `SmartyDo.jar` file to start SmartyDo. You will be greeted with a simple interface that has three components: a **Visual Panel**, a **Message Box** and a **Command Bar**.

<img src="images/mainScreen.png" width="250"><br>Figure 1. Welcome Screen<br>

**Command Bar** is where you enter short commands to tell SmartyDo what to do.<br>
**Visual Panel** is where you can see a comprehensive list of your tasks.<br>
**Message Box** shows the result of your command.<br>

## 3. Getting Started
In this section, you will be introduced to the various commands that you will need when using SmartyDo. These commands will be described to you in the format described below.
**Command Format**<br>
Words in `lower case` are the command.<br>
Words in `upper case` are the parameters.<br>
Items in `square brackets` are optional.<br>
The order of parameters is flexible. <br>

### 3.1. **Requesting Help From SmartyDo**

You can use the help command to gain access to this user guide should you need any help with the commands and their format. Should you enter an invalid command (e.g. `abcd`), information will be shown, when possible, to help correct your mistakes. You may also access this function through a keyboard shortcut.<br>

Format: `help`
Keyboard Shortcut: `Ctrl+F1`

**Example:**<br>
If you wish to get help on using SmartyDo, you may enter `help` into the Command Bar.

<img src="images/help.png" width="500" align="middle"><br>Figure 2. SmartyDo's Help Command<br>

After entering the command, a new window will appear showing you a summary of all commands and keyboard shortcuts.

### 3.2. **Choosing Your Save Location**

You can choose where to save your data on your computer by using the `save` command. The save location will be referenced from the directory in which SmartyDo is stored. From this point, all data will be saved to the file you specified.

Format: `save FILEPATH.xml`

**Example:**<br>
If you wish to save your files to the filepath data/todolist.xml, you may enter `save data/todolist.xml` into the Command Bar.

<img src="images/saveCommand.png" width="500" align="middle"><br>Figure 3. SmartyDo's Save Command<br>

After entering the command, MessageBox will show you if your new save file has been successfully created.

### 3.3. **Loading Save Files**
You can load different save files from your computer into SmartyDo by using the `load` command. The location from which your save file is retrieved will be referenced from the directory in which SmartyDo is stored. From this point, all data will be saved to the file you specified.

Format: `load FILEPATH.xml`

**Example:**<br>
If you wish to load a previously saved file stored in data/my_todo_list.xml, you may enter `load data/my_todo_list.xml` into the Command Bar.

<img src="images/loadCommand.png" width="500" align="middle"><br>Figure 4. SmartyDo's Load Command<br>

After entering the command, MessageBox will show you if the save file has been successfully loaded to SmartyDo.

### 3.4. **Adding Tasks Into SmartyDo**

You can add a task into SmartyDo by using the add command. There are number of parameters that you can use to add more details to the task. Below is a summary of the various parameters and their usage:<br>

Here is the summary of the parameters and their usage:

| Parameter     |     Flag     |   Format Requirements    | Optional |
| ------------- |:-------------:| -----:|:---:|
| `TASK_NAME`   |     | _**/n**_ required if `TASK_NAME` is not the first parameter | No |
| `DATE_TIME` |    **t;**  | [Date] [Start_Time] [End_Time] , delimited by spaces    | Yes |
| `TAG`         | _**t/**_ | alphanumeric | Yes |
| `LOCATION` | _**a;**_ |  alphanumeric  | Yes |
| `DESCRIPTION` | _**d;**_ | alphanumeric | Yes |
Table 3.2. Add Command Parameters

- `TASK_NAME` is the name of the task and this parameter is compulsary.
- `Date` is the date of the task supports date format of
dd/mmm/yyyy eg:20-Jan-2017 and dd/mm/yyyy with `/` interchangleable with `.` and `-` eg: 20-01-2017.
- `START_TIME` and `END_TIME` is the starting time and ending time of the task respectively. You may consider to use these parameters when starting time and/or deadline is known. You may omit the details of 'DATE_TIME' which will result in a task that has no time frame.
- `TAG` is the characteristic you can add to the task. Such tags can be "Urgent", "HighPriority" and etc.
- `LOCATION` is the place of task being done. You can use this parameter to remind you where to go to complete the task.
- 'DESCRIPTION' is where you can include some extra information about your task.

Format : `add TASK_NAME [t; DATE START_TIME] [a;LOCATION] [t/TAG] [d;`

> You don't have to enter the optional parameters when you don't need them. The order of the parameters are not fixed. You can enter the parameters in any order. For example, `add t/[TAG] t; DATE START_TIME ` is also correct format.

**Example:**<br>
Let's say you want to add task named "Presentation" which is scheduled for 18 July 2016, 9:00AM. All you need to do is enter the following as shown below.

<img src="images/addCommand.png" width="250" align="middle"><br>Figure 5. Example of add command<br>

After entering the command, MessageBox will show you task is successfully added into SmartyDo and you will see the updated list of task in the VisualBox.

### 3.5. **Editing Task Details**

You might want to edit details of a task for several reasons. For example, when deadline was extended you will need to update the SmartyDo for this change. Using `edit` command will solve this problem.

Format: `edit INDEX PARAMETER_TYPE NEW_VALUE`

PARAMETER_TYPE the type of the parameter we want to change and NEW_VALUE is the new value for that parameter. <br>
`edit` command edits the task at the specified INDEX. You can easily identify the INDEX of the task by looking at the Visual Panel. <br>
If the task you want to edit is not shown in the Visual Panel, you can use `view` or `find` command to find the required task. <br>

**Example:**<br>
Let's say you want to add deadline time for task named "Presentation". Then, you must first check the INDEX of that task. In this case, the INDEX of the task is 1. So to add deadline for this task, enter `edit 1 t; DEADLINE`. This will update the deadline of the task. A demonstration of this functionality shown below.

<img src="images/addeditCommand.png" width="500" align="middle"><br>Figure 6. Before(left) and after(right) of an edit command<br>

### 3.6. **Deleting Tasks**

Sometimes, you may also want to delete tasks due to unexpected circumstances. To help you to handle such problem, `delete` command can be used. `delete` command is simply deleting task from SmartyDo.

Format: `delete INDEX`

Similar to `edit` command, `delete` command also uses INDEX. INDEX can be found in Visual Panel by using `view` command and `find` command.

**Example:**<br>
If you want to delete specfic task, find the INDEX of that task. Let's say the INDEX is 1. Then, enter `delete 1` in the command bar.

<img src="images/adddeleteCommand.png" width="500" align="middle"><br>Figure 7. Example of delete command<br>

After entering `delete` command, SmartyDo will delete the task specified by the INDEX and will show the updated list in the Visual Panel. In the screenshot above, you can see that the "Presentation" task has been deleted from SmartyDo.

### 3.7. **Marking Completed Tasks**

Instead of deleting the task, you may want to mark the task as complete and store the details of the task in the SmartyDo. In this case, you can use `done` command. By using `done` command, you can easily identify the completed tasks from the list.

Format: `done INDEX`

Similar to `delete` command and `edit` command, INDEX is used in `done` command.

**Example:**<br>
You have now completed the task named "Presentation" and now you want to mark this task as complete. To do this, you will need to check the INDEX of this task. In this case, INDEX of this task is 1. So, entering `done 1` will mark "User Guide" task as complete.

<img src="images/doneCommand.png" width="250" align="middle"><br>Figure 8. Example of done command<br>

After entering the `done` command, you are now able to identify the completed task easily from the list.

### 3.8. **Undoing and Redoing**

With `undo`, you are allowed to reverse your previous changes sequentially while `redo` allows you to reverse the change done by `undo`.<br>

- `undo` command requires the application to have executed atleast one undoable command after launching.
- `redo` command requires the application to have executed atleast one succussful `undo` command after launching.

Undoable Commands:`add`, `delete`, `edit`, `done`

> SmartyDo **does not store** history of actions in your computer.
> Your history of actions resets when SmartyDo is launched.
> Also, if you enter any undoable command after entering `redo` or `undo`, your history of actions would be _**removed**_. <br>

Format: `undo`, `redo`

**Example:**<br>
Let's say you have added a task and your friend told you that your tutor has changed the date. You would like to undo it. You can undo it as long as you just added it, as shown below.

<img src="images/doundoCommand.png" width="500" align="middle"><br>Figure 9.1. Before(left) and after(right) of an undo command<br>

By entering `undo` command, SmartyDo updates your list of tasks to how it was before you executed an undoable action. From the screenshot above, you can see that the date of the task named "Presentation" had changed.

However, you realized that your friend was wrong and you want to change the date back again. In this case, you do not need to use edit command again. Instead you can simply use `redo` command, as shown below.

<img src="images/undoredoCommand.png" width="500" align="middle"><br>Figure 9.2. Before(left) and after(right) of an undo command<br>

By using `redo` command, SmartyDo updates your list of tasks to how it was before you executed `undo` command. From the screenshot above, you can see that the "Presentation" task has been restored to its previous state.

### 3.9. **Selecting Specific Tasks**

Select the task identified by the parameter. A full detailed description will appear in a pop up window.

Format: `select PARAM`

**Example:**<br>
Let's say you want to know detailed information about the third task in the Visual Panel. All you need to do is enter `view 3` into command bar, just as shown below.

<img src="images/view3Command.png" width="500" align="middle"><br>Figure 10. Example of view command<br>

After entering the command, Browser Panel will show a detailed description about task 3.

### 3.10. **Finding Specific Tasks**

If you want to find tasks that contain specific keyword in their name, you can use `find` command. `find` command is a command that will list all the tasks matching atleast one keyword. You can enter more than one keyword for `find` command.

Format: `find KEYWORD [MORE_KEYWORDS]`

<img src="images/findCommand.png" width="250" align="middle"><br>Figure 11. Example of find command<br>

> Beware that keywords are case sensitive and only the task's name is searched. However, the order of the keywords does not matter. e.g. `find cs2103 project` is same as `find project cs2103`

### 3.11. **Filtering the Task List**

You can filter the list of tasks that you are viewing on the Visual Panel.

Format: `view KEYWORD` where KEYWORD in this case are any of the following: ALL/OVERDUE/UPCOMING/COMPLETED/INCOMPLETE

For example, after finding specific tasks, to return the Visual Panel back to where it lists all the tasks, simply input `view ALL` just as shown below.

<img src="images/listAllCommand.png" width="250" align="middle"><br>Figure 12. Example of list command<br>

### 3.12. **Locating a Destination**
You may search for destinations listed in the LOCATION parameter of your task by using the `locate` command. A separate window will appear showing the details of the location mentioned (if any) in your task. Each task can be referred to by the index displayed in front of its title. 

Format: `locate INDEX`

**Example:**<br>
If you wish to search for the location of the task named Presentation which has the index of 1, you may enter `locate 1` into the Command Bar.

<img src="images/locateCommand.png" width="250" align="middle"><br>Figure 13. Example of locate command<br>

After entering the command, a new window will appear showing you the details of the task you requested.


### 3.14. **Exiting SmartyDo**

After using SmartyDo, you can exit the program by using `exit` command.

Format: `exit`

By entering `exit` command in the command box, SmartyDo will quit and save the data.

## 4. Smart Features

### 4.1. **FlexiCommand**
It is okay if you cannot remember the syntax entirely! As long as you remember the keyword some reshuffling of the parameters entered is fine. Our program will ask you for confirmation if we are unsure what you want.

### 4.2. **Saving the Data**
SmartyDo will automatically save your data in the hard disk after any command that changes the data. There is no need to save manually.

## 5. Summary

### 5.1. **Command Summary**
|**Command**|**Parameters**|**Format**|
|:---------:|:--------:|-------|
|Help   || `help`|
|Save     |FILEPATH.xml|`save FILEPATH.xml`|
|Load     |FILEPATH.xml|`load FILEPATH.xml`|
|Add      |TASK_NAME, DATE_TIME,[LOCATION], [TAG] |`add /n TASK_NAME d; DATE START_TIME END_TIME t/ TAG a; LOCATION `|
|Edit     |INDEX|`edit INDEX`|
|Delete   |INDEX|`delete INDEX`|
|Done     |INDEX|`done INDEX`|
|Undo     ||`undo`|
|Redo     ||`redo`|
|Select   |INDEX|`select INDEX`|
|Find     |KEYWORD, [MORE_KEYWORD]|`find KEYWORD [MORE_KEYWORD]`|
|View     |PARAM|`view PARAM`|
|Locate   |INDEX|`locate INDEX`|
|Clear    ||`clear`|
|Exit     ||`exit`|
Table 5. Command Summary

### 5.2. **Keyboard Shortcuts**
|**Command**|**Shortcut**|
|:---:|:---:|
|help|`Ctrl+F1`|
|list all| `Ctrl+1`|
|list overdue| `Ctrl+2`|
|list upcoming| `Ctrl+3`|
|list completed| `Ctrl+4`|
|list incomplete| `Ctrl+5`|