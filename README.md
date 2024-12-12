# 1. What is phpx
Phpx is a php extension and its interpreter, which helps to create some of php's functionality more easily.
The interpreter itself is written in java and requires JDK for java 22 or newer to operate.
## WHY?
I am a lazy man, so when I see:
```php
<?php if($value > 3) { ?>
    <p>The value is greater than three!<p>
<?php } ?>

```
**And yes, it works.**

I think "it is too much to write", so instead I'll waste my time creating an extension, which will never be used by anyone except for me.
So now I can simply write:
```phpx
?if ($value > 3)
    <p>The value is greater than three!<p>
/?
```

# 2. Syntax
phpx uses file with .phpx extension, and rewrites them into plain php files, current syntax includes:
## ?if
**usage**: plain if statement

**syntax**: `?if (<condition>)`

**example**: 
```phpx
?if ($condition)
    <p>The condition is true</p>
```
## ?while
**usage**: plain while loop

**syntax**: `?while (<condition>)`

**example**: 
```phpx
?while ($condition)
    <p>Loop element</p>
```
## ?for
**usage**: plain for loop

**syntax**: `?for (<loop components>)`

**example**: 
```phpx
?for ($i = 0; $i < 3; $i++)
    <p>Loop element</p>
```
## /?
**usage**: close brace, **important: you have to close each string-consisted tag starting with ?**

**syntax**: `/?`

**example**: 
```phpx
?if ($condition)
    <p>The condition is true</p>
/?
```
## ?{ and ?}
**usage**: open php tag and close php tag, plain php code can be inserted in between of them

**syntax**: `?{ <code> ?}`

**example**: 
```phpx
?{
    $condition = $name == "William";
?}
```
# 3. Interpreter
## 3.1 Installation
I'm sorry to inform that the project is not ready for public release yet.
## 3.2 Commands
### no arguments
interprets whole project and puts it into *out* directory
```bash
phpx
```
### filename
interprets the file specified and puts it into *out* directory
```bash 
phpx filename.phpx
```
### \[generate/g] \[f/file]|\[t/temp/template] filename
creates a new .phpx file or template and puts it in current directory
```bash
phpx g f index
phpx generate template car
```
# 4. Projects
Project is a directory consisted of:
- .xpj - project file
- out - php output directory
- .phpx files
## 4.1 Creating a project
Currently, there is no way to create a project other than manually.

In order to set up a project manually, you have to:
1. create a .xpj file with no name
2. open the project file
3. write project name in the first line
4. save the file

After that you are able to use `phpx generate` command and new files will be automatically added to the project file
