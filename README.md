# DSC180-Capstone-Web

### Background

The purpose of phrase mining is to extract high-quality phrases from a large amount of text corpus. It identifies the phrases instead of an unigram word, which provides a much more understanding of the text.  In this study, we apply AutoPhrase method into two different datasets and compare the decreasing quality ranked list of phrase ranked list in multi-words and single word. Our datasets are from the abstract of Scientific papers in English with the English knowledge base from Wikipedia.

In the front-end web development, we built the front webs. We utilize hyper text markup language(HTML) to structure and design in a web browser with the assistance of Cascading style sheeting(CSS) - style presentation and JavaScript(JS) enabled to interactive web pages. Furthermore, we utilized LayUI as a front-end UI framework to make it more organized. It is usable for users to upload their own files, the knowledge base, showing the processing steps from autophrase and some text and word cloud visualization at the end. 	

In the back-end web development, we use Spring MVC and Spring as the application framework to control the back-end of the web with JDK and Tomcat required implementation and configuration. It will read the input file and stored into the web folder; call the run.py file to start the AutoPhrase processing; read the text's result; read and store the images; and produce the top 20 valuable phrases with corresponding size in the word cloud.


### Purpose of the Code

By simply entering/attaching their desired input corpus, the user would get the mined phrases instantly, along with a more comprehensive view of the output with our auto-generated dashboard.

### How to run the Code

To run this website, please follow the below steps:
1. On a terminal:

   (a) docker build --no-cache -t web .
   
   (b) docker run -it --rm -p 8080:8080 web
2. On a browser open: http://localhost:8080/python

OR

Visit our website:

http://35.223.187.250:8080/python




### Work Cited

Professor Jingbo Shang’s Github: https://github.com/shangjingbo1226/AutoPhrase

Jingbo Shang, Jialu Liu, Meng Jiang, Xiang Ren, Clare R Voss, Jiawei Han, "Automated Phrase Mining from Massive Text Corpora", accepted by IEEE Transactions on Knowledge and Data Engineering, Feb. 2018.





### Responsibilities

Yicen worked on the majority portion of front-end and back-end development of the webs.


Tiange worked on some front-end web building, tried to add the knowledge base.


Anant worked on integrating the website on docker and DSMLP.
