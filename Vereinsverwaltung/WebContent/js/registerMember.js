/**
 * 
 * @author tobi
 */
function checkNewMember()
{
	var datereg = /^\s*(3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2})\s*$/;
	
	var birth_date = document.getElementById("birth_date_field").value;
	var entry_date = document.getElementById("entry_date_field").value;
	if(!(datereg.test(birth_date)))
	{
		document.getElementById("birth_date_field").style.borderColor="#ff0000";
		document.getElementById("birth_date_field").focus();
		alert("nicht korrekt");
		return false;
	}
	else
	{
		document.getElementById("birth_date_field").style.borderColor="green";
		document.getElementById("birth_date_field").focus();
	}
	
	if(!(datereg.test(entry_date)))
	{
		document.getElementById("entry_date_field").style.borderColor="#ff0000";
		document.getElementById("entry_date_field").focus();
		alert("nicht korrekt");
		return false;
	}
	else
	{
		document.getElementById("entry_date_field").style.borderColor="green";
		document.getElementById("entry_date_field").focus();
		return true;
	}
}