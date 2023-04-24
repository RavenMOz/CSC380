import React from 'react';
import {Nav, NavMenu} from './NavbarElements';

function clearOnClick()
{
    alert("Clear");
}

function saveOnClick()
{
    alert("Save");
}

function closeOnClick()
{
    alert("Close");
}

function shareOnClick()
{
    alert("Share");
}
  
const Navbar = () => {
  return (
    <Nav>
        <NavMenu>
            <button onClick={clearOnClick} style={{position: 'absolute'}}>
                Clear
            </button>
            <button onClick={saveOnClick} style={{position: 'absolute', left: `500px`}}>
                Save
            </button>
            <button onClick={closeOnClick} style={{position: 'absolute', left: `750px`}}>
                Close
            </button>
            <button onClick={shareOnClick} style={{position: 'absolute', left: `1000px`}}>
                Share
            </button>
        </NavMenu>
    </Nav>
  );
};
  
export default Navbar;