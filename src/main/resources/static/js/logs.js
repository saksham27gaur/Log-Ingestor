
var enteredSearchString = '';
function filterFunction() {
    var keyword = document.getElementById('keyword').value.trim();
    var level = document.getElementById('level').value.trim();
    var message = document.getElementById('message').value.trim();
    var resourceId = document.getElementById('resourceId').value.trim();
    var traceId = document.getElementById('traceId').value.trim();
    var spanId = document.getElementById('spanId').value.trim();
    var commit = document.getElementById('commit').value.trim();
    var parentResourceId = document.getElementById('parentResourceId').value.trim();
    var startDate = document.getElementById('startDate').value.trim();
    var endDate = document.getElementById('endDate').value.trim();
    console.log(keyword)
    location.href = "/interface/search-filter?keyword=" + keyword + "&level=" + level + "&message=" + message +
        "&resourceId=" + resourceId + "&traceId=" + traceId + "&spanId=" + spanId +
        "&commit=" + commit + "&parentId=" + parentResourceId + "&startDate=" + startDate + "&endDate=" + endDate;
}

function resetFilter() {
    var keyword = document.getElementById('keyword').value.trim();
    document.getElementById('level').value = "";
    document.getElementById('message').value = "";
    document.getElementById('resourceId').value = "";
    document.getElementById('traceId').value = "";
    document.getElementById('spanId').value = "";
    document.getElementById('commit').value = "";
    document.getElementById('parentResourceId').value = "";
    document.getElementById('startDate').value = "";
    document.getElementById('endDate').value = "";
    location.href = "/interface/search-filter?keyword=" + keyword;
}

document.getElementById('keyword')
    .addEventListener('keyup', function(event) {
        if (event.keyCode === 13) {
            var keyword = document.getElementById('keyword').value.trim();
            console.log(keyword)
            location.href = "/interface/search-filter?keyword=" + keyword;
        }
    });

  function handleKeyPress(event) {
            if (event.key === 'Enter') {
                highlightSearchTerm();
            }
        }

    function highlightSearchText() {
        var searchText = document.getElementById('keyword').value.toLowerCase();
        var table = document.getElementById('data_table');
        var rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');

        for (var i = 0; i < rows.length; i++) {
            var messageCell = rows[i].getElementsByTagName('td')[1]; // Assuming the message is in the second column

            // Get the original text content
            var originalText = messageCell.textContent.toLowerCase();

            // Check if the search text is present in the original text
            if (originalText.includes(searchText)) {
                // Highlight the matching text using <mark>
                var highlightedText = originalText.replace(new RegExp(searchText, 'gi'), function(match) {
                    return '<mark>' + match + '</mark>';
                });

                // Update the cell content with the highlighted text
                messageCell.innerHTML = highlightedText;
            } else {
                // If the search text is not present, reset the content to the original text
                messageCell.innerHTML = originalText;
            }
        }
    }

function removeHighlight(table) {
    // Remove highlighting by replacing the innerHTML with the original text
    var cells = table.getElementsByTagName('td');
    for (var i = 0; i < cells.length; i++) {
        cells[i].innerHTML = cells[i].innerText || cells[i].textContent;
    }
}
