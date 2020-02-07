from tkinter import *


class Preferences:
    users = {}

    def __init__(self):
        pass

    def addUser(self):
        pass

    def removeUser(self):
        pass

    # config
    window = Tk()
    window.title("Preferences")
    window.geometry('500x700')
    positionRight = int(window.winfo_screenwidth() / 2 - 500 / 2)
    positionDown = int(window.winfo_screenheight() / 3 - 700 / 2)

    #Positions the window in the center of the page.
    window.geometry("+{}+{}".format(positionRight, positionDown))
    window.configure(bg='#373737')

    window.resizable(False, False)

    # Faces
    facesLabel = Label(window, text="Faces", font=("Helvetica Bold", 30), foreground='#ffffff', bg='#373737')
    facesLabel.grid(column=0, row=1, sticky=W, padx=10, pady=10)

    # general
    generalLabel = Label(window, text="General", font=("Helvetica Bold", 30), foreground='#ffffff', bg='#373737')

    timeoutLabel = Label(window, text="Turn off screen after (minutes)", font=("Helvetica", 15),
                         foreground='#ffffff',
                         bg='#373737')
    timeoutSpinBox = Spinbox(window, from_=0, to=30, width=5, foreground='#ffffff', bg='#373737')
    notificationCheck_state = BooleanVar()
    notificationCheck_state.set(False)
    notificationCheck = Checkbutton(window,
                                    text='Notify when the computer turns off',
                                    var=notificationCheck_state, foreground='#ffffff', bg='#373737')

    shortCutCheck_state = BooleanVar()
    shortCutCheck_state.set(False)
    shortCutCheck = Checkbutton(window, text='Shortcut', var=shortCutCheck_state, bg='#373737',
                                foreground='#ffffff')

    darkModeCheck_state = BooleanVar()
    darkModeCheck_state.set(False)
    darkModeCheck = Checkbutton(window, text='Dark mode', var=darkModeCheck_state, bg='#373737',
                                foreground='#ffffff')

    facesLabel.grid(column=0, row=0, sticky=W, padx=10, pady=10)
    i = 0

    # Users
    for user in users:
        userLabel = Label(window,
                          text=users[i]["firstname"] + " " + users[i]["lastname"],
                          font=("Helvetica", 20), bg='#373737', foreground="#ffffff")
        removeUser = Button(window, text="x", width=1, bg='#373737', activeforeground='#373737',
                            command=removeUser)
        i += 1
        userLabel.grid(column=0, row=i, sticky=W, padx=20, pady=5)
        removeUser.grid(column=1, row=i, sticky=E, pady=5)

        darkModeCheck_state = BooleanVar()
        darkModeCheck_state.set(False)
        darkModeCheck = Checkbutton(window, text='Dark mode', bg='#373737', var=darkModeCheck_state,
                                    foreground="#ffffff")

    addUser = Button(window, text="+", width=1, command=addUser)
    addUser.grid(column=0, row=i + 1, sticky=W, padx=20, pady=5)

    # advanced
    advancedlabel = Label(window, text="Advanced", font=("Helvetica Bold", 30), bg='#373737', foreground='#ffffff')
    aboutLabel = Label(window, text="About", font=("Helvetica Bold", 30), bg='#373737', foreground='#ffffff')
    faceLockLabel = Label(window, text="FaceLock", font=("Helvetica Bold", 25), bg='#373737', foreground='#ffffff')

    strangerNotificationCheck_state = BooleanVar()
    strangerNotificationCheck_state.set(False)
    strangerNotificationCheck = Checkbutton(window,
                                            text='Notify when someone is seen using the computer',
                                            var=strangerNotificationCheck_state,
                                            bg='#373737',
                                            foreground='#ffffff')

    version = Label(window, text="0.1 Beta", font=("Helvetica", 15), bg='#373737', foreground='#ffffff')
    copyrigh = Label(window, text="Copyright 2020 - LMBJ", font=("Helvetica", 15), bg='#373737',
                     foreground='#ffffff')

    # paint
    generalLabel.grid(column=0, row=i + 2, sticky=W, padx=10, pady=10)
    timeoutLabel.grid(column=0, row=i + 3, sticky=W, padx=20, pady=10)
    timeoutSpinBox.grid(column=1, row=i + 3, sticky=E, pady=10)
    notificationCheck.grid(column=0, row=i + 4, sticky=W, padx=20, pady=10)
    shortCutCheck.grid(column=0, row=i + 5, sticky=W, padx=20, pady=10)
    darkModeCheck.grid(column=0, row=i + 6, sticky=W, padx=20, pady=10)
    advancedlabel.grid(column=0, row=i + 7, sticky=W, padx=10, pady=10)
    strangerNotificationCheck.grid(column=0, row=i + 8, sticky=W, padx=20, pady=10)
    aboutLabel.grid(column=0, row=i + 9, sticky=W, padx=10, pady=10)
    faceLockLabel.grid(column=0, row=i + 10, sticky=W, padx=10, pady=10)
    version.grid(column=0, row=i + 11, sticky=W, padx=10, pady=10)
    copyrigh.grid(column=0, row=i + 11, sticky=W, padx=10, pady=10)
    window.mainloop()