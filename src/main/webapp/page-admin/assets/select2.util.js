$(document).ready(function() {
	this.setValueForSelect2 = function(select2, id, text) {
		if (select2.find("option[value='" + id + "']").length) {
			select2.val(id).trigger('change');
		} else {
			// Create a DOM Option and pre-select by default
			var newOption = new Option(text, id, true, true);
			// Append it to the select
			select2.append(newOption).trigger('change');
		}
	}
});