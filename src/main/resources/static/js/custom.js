function expandCollapseTable(tableId, btnId) {
	var table = document.getElementById(tableId);
	var btn = document.getElementById(btnId);
				
	if (btn.innerHTML == '+') {
		table.hidden = false;
		btn.innerHTML = '-';
	} else if (btn.innerHTML == '-') {
		table.hidden = true;					
		btn.innerHTML = '+';
	}
};
			
