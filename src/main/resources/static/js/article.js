const deleteButton = document.getElementById("delete-btn");

if (deleteButton) {
    deleteButton.addEventListener('click', () => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        }).then((response) => {
            if (response) {
                alert('삭제 완료');
                location.replace('/articles');
            } else {
                alert('삭제 실패');
            }
        });
    });
}

const modifyButton = document.getElementById("modify-btn");

if (modifyButton) {
    modifyButton.addEventListener('click', () => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert('수정 완료');
            location.replace(`/articles/${id}`);
        });
    });
}

const createButton = document.getElementById("create-btn");

if (createButton) {
    createButton.addEventListener('click', () => {
        fetch('/api/articles', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert('생성 완료');
            location.replace('/articles');
        });
    });
}