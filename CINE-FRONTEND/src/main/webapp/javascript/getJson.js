function getJSON(url, data, callback) {
    fetch(url, {
        method: 'POST',
        body: data
    }).then(result => {
        if (result.status === 200) {
            return result.json();
        } else {
            console.error(`HTTP error: ${result.status}`);
        }
    }).then(
            callback
            ).catch(errCode => {
        console.error(errCode);
    });
}