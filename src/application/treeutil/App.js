import './App.css';
import "./styles.css";
import './SideBar.css';
import React, {Component} from "react";
import TextField from '@mui/material/TextField';
import Navbar from './components/Navbar';
import {slide as Menu} from 'react-burger-menu';
import {BrowserRouter as Router} from 'react-router-dom';

function member(name)
{
    return {
        memberID: "",
        name: name,
        biography: "",
        birthday: "",
        children: {},
        childCount: 0,
        parent1: null,
        parent2: null,
        spouse: null,
        drawnFlag: false,
        buttonLayoutX: 0,
        buttonLayoutY: 0,
        topLine: null,
        bottomLine: null,
        lineList: {},
        lineCount: 0,

        getMemberID()
        {
            return this.memberID;
        },

        setMemberID(newMemberID)
        {
            this.memberID = newMemberID;
        },

        getName()
        {
            return this.name;
        },

        setName(newName)
        {
            this.name = newName;
        },

        getBiography()
        {
            return this.biography;
        },

        setBiography(newBiography)
        {
            this.biography = newBiography;
        },

        getBirthday()
        {
            return this.birthday;
        },

        setBirthday(newBirthday)
        {
            this.birthday = newBirthday;
        },

        addChild(newChild)
        {
            this.children[this.childCount] = newChild;
            this.childCount++;
        },

        removeChild(targetChild)
        {
            for(let count = 0; count < this.childCount; count++)
            {
                if(this.children[count] === targetChild)
                {
                    this.children[count] = null;
                    this.childCount--;
                }
            }
        },

        // Clears all Children
        clearChildren()
        {
            this.children.removeAll(this.children);
            this.childCount = 0;
        },

        getChild(targetChild)
        {
            if(this.children.contains(targetChild))
            {
                for(let count = 0; count < this.childCount; count++)
                {
                    if(this.children[count] === targetChild)
                    {
                        return this.children[count];
                    }
                }
            }

            return null;
        },

        getChildFromIndex(index)
        {
            return this.children[index];
        },

        getChildFromName(name)
        {
            for(let count = 0; count < this.childCount; count++)
            {
                if(this.children[count].getName().localeCompare(name))
                {
                return this.children[count];
                }

                else
                {
                return null;
                }
            }
        },

        getChildCount()
        {
            return this.childCount;
        },

        hasChildren()
        {
            if(this.childCount !== 0)
            {
                return true;
            }

            else
            {
                return false;
            }
        },

        // Adds a Parent
        addParent(newParent)
        {
            if(this.parent1 === null)
            {
                this.parent1 = newParent;
            }

            else if(this.parent2 === null)
            {
                this.parent2 = newParent;
            }
        },

        // Removes a Parent
        removeParent(targetParent)
        {
            if(this.parent1 === targetParent)
            {
                this.parent1 = null;
            }

            else if(this.parent2 === targetParent)
            {
                this.parent2 = null;
            }  
        },

        clearParents()
        {
            this.parent1 = null;
            this.parent2 = null;
        },

        getParent1()
        {
            return this.parent1;
        },

        getParent2()
        {
            return this.parent2;
        },

        hasParent1()
        {
            if(this.parent1 !== null)
            {
                return true;
            }

            else
            {
                return false;
            }
        },

        hasParent2()
        {
            if(this.parent2 !== null)
            {
                return true;
            }

            else
            {
                return false;
            }
        },

        hasTwoParents()
        {
            if(this.parent1 !== null && this.parent2 !== null)
            {
                return true;
            }

            else
            {
                return false;
            }
        },

        hasOneParent()
        {
            if(this.parent1 !== null ^ this.parent2 !== null)
            {
                return true;
            }

            else
            {
                return false;
            }
        },

        hasNoParents()
        {
            if(this.parent1 === null && this.parent2 === null)
            {
                return true;
            }

            else
            {
                return false;
            }
        },

        // Adds a Spouse
        addSpouse(newSpouse)
        {
            if(this.spouse == null)
            {
                this.spouse = newSpouse;
            }
        },

        // Removes a Spouse
        removeSpouse()
        {
            this.spouse = null;
        },

        // Gets the Spouse
        getSpouse()
        {
            return this.spouse;
        },

        hasSpouse()
        {
            if(this.spouse !== null)
            {
                return true;
            }

            else
            {
                return false;
            }
        },

        getDrawnFlag()
        {
            return this.drawnFlag;
        },

        hasBeenDrawn(flag)
        {
            this.drawnFlag = flag;
        },

        getLayoutX()
        {
            return this.buttonLayoutX;
        },

        setLayoutX(newX)
        {
            this.buttonLayoutX = newX;
        },

        getLayoutY()
        {
            return this.buttonLayoutY;
        },

        setLayoutY(newY)
        {
            this.buttonLayoutY = newY;
        },
    };
}

function family()
{
    return {
        familyMembers: {}, // List of Members in the Family
        root: null,              // First Member added to the Family
        memberCount: 0,

        // Adds a Member to the Tree
        addMember(newMember)
        {
            this.familyMembers[this.memberCount] = newMember;
            this.familyMembers[this.memberCount].setMemberID("member" + this.memberCount);
            this.memberCount++;

            if(this.memberCount === 1)
            {
                this.root = newMember;
            }
        },

        // Removes a Member from the Tree
        removeMember(targetMember)
        {
            this.familyMembers.remove(targetMember);
        },
                
        // Clears all Members in the Tree
        clearMembers()
        {
            this.familyMembers.clear();
        },

        // Returns the desired Member from the Tree
        getMember(targetMember)
        {
            for(let count = 0; count < this.memberCount; count++)
            {
                if(this.familyMembers[count] === targetMember)
                {
                    return this.familyMembers[count];
                }
            }

            return null;
        },

        getMemberFromIndex(index)
        {
            return this.familyMembers[index];
        },

        getMemberFromMemberID(id)
        {
            for(let count = 0; count < this.memberCount; count++)
            {
                if(this.familyMembers[count].getMemberID() === id)
                {
                    return this.familyMembers[count];
                }
            }

            return null;
        },

        getRootMember()
        {
            return this.root;
        },

        setRootMember(targetMember)
        {
            for(let count = 0; count < this.memberCount; count++)
            {
                if(this.familyMembers[count] === targetMember)
                {
                    this.root = this.familyMembers[count];
                }
            }
        },

        getSize()
        {
            return this.memberCount;
        },
    };
}

  let person1 = member('');
  let person2 = member('');
  let myFamily = family();

  person1.setLayoutX(250);
  person1.setLayoutY(275);
  person1.addChild(member('John'));
  person1.addChild(member('Greg'));
  person1.addChild(member('Paul'));
  person1.addParent(member('Rebecca'));
  person1.addParent(member('Bob'));
  person1.addSpouse(member('Rachel'));
  person1.getSpouse().addParent(member('Tony'));

  myFamily.addMember(person1);
  myFamily.addMember(person2);     

class App extends Component
{
    state = 
    {
      xoffset: 700,
      yoffset: 400,
      opacity: 1
    };

    state2 = 
    {
      xoffset: 900,
      yoffset: 400,
      opacity: 1
    };
     
    addMember = () => 
    {
        this.setState(
        { 
            xoffset: 375,
            yoffset: 250,
            opacity: 1,
        });
    };

    onClick(e)
    {
        if(document.getElementById(e.currentTarget.id).innerHTML !== 'None')
        {
            var curMember = myFamily.getMemberFromMemberID(e.currentTarget.id);
            myFamily.setRootMember(curMember);
            document.getElementById("currentHeader").innerHTML = 'Current Member: ' + myFamily.getRootMember().getName();
        }
    }

    onKeyPress(e)
    {
        if(e.keyCode === 13)
        {
            var curMember = myFamily.getRootMember();
            document.getElementById(curMember.getMemberID()).innerHTML = document.getElementById("memberName").value;
            document.getElementById("currentHeader").innerHTML = 'Current Member: ' + document.getElementById("memberName").value;
        }
    }
    
    render() 
    {
      return (
        <div>
            <div className="App">
            <Router>
                <Navbar/>
            </Router>
            <Menu>
                <h5 id="currentHeader">Current Member: None</h5>
                <TextField onKeyDown={this.onKeyPress}
                    id="memberName"
                    label="Member Name"
                    margin="normal"
                />
                <TextField
                    id="biography"
                    label="Biography"
                    margin="normal"
                />
                <TextField
                    id="birthday"
                    label="Birthday"
                    margin="normal"
                />
                <button>
                    Add Spouse
                </button>
                <TextField
                    id="spouseName"
                    label="Spouse Name"
                    margin="normal"
                />
                <button>
                    Add Parent
                </button>
                <TextField
                    id="parentName"
                    label="Parent Name"
                    margin="normal"
                />
                <button>
                    Add Child
                </button>
                <TextField
                    id="childame"
                    label="Child Name"
                    margin="normal"
                />
            </Menu>
                <button id={person1.getMemberID()} onClick={this.onClick}
                    style={{
                    position: "absolute",
                    left: `${this.state.xoffset}px`,
                    top: `${this.state.yoffset}px`,
                    opacity: `${this.state.opacity}`
                    }}
                >
                    None
                </button>
                <button id={person2.getMemberID()} onClick={this.onClick}
                    style={{
                    position: "absolute",
                    left: `${this.state2.xoffset}px`,
                    top: `${this.state2.yoffset}px`,
                    opacity: `${this.state2.opacity}`
                    }}
                >
                    None
                </button>
            </div>
            <div style={{ marginTop: "80px" }}>
                <button onClick={this.addMember} style={{position: 'absolute', left: `1300px`, top: `600px`}}>
                    Add Member
                </button>
            </div>
        </div>
      );
    }
}

export default App;