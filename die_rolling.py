import random
from Tkinter import *
import tkMessageBox
minNumber = 1
maxNumber = 6



window1=Tk()
window1.title("Dice Rolling")
window1.geometry('1000x700+1670+500')

label1 = Label(window1,text="Make your dice and roll em !!", height = 5, font = (20)).pack(side=TOP)

minValue=IntVar()
maxValue=IntVar()

label2 = Label(window1,text="Minimum value on Dice",height=2, font = (20)).pack(padx=200)
textfield1 = Entry(window1, text=minValue, font= (20), bd=5, width =10).pack()
label3 = Label(window1,text="Maximum value on Dice",height = 2, font = (20)).pack()
textfield2 = Entry(window1, text=maxValue, font= (20), bd=5, width=10).pack()

def roll():
	minNumber = minValue.get()
	maxNumber = maxValue.get()

	value = random.randint(minNumber, maxNumber)

	window1.option_add('*Dialog.msg.font', 'Helvetica 12')
	tkMessageBox.showinfo(title="Rolling... " , message= "You rolled "+str(value))


button1 = Button(window1, text="Roll",command = roll, font=(20)).pack(pady=100)

window1.mainloop()