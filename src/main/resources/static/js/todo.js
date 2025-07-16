// 백엔드 API 서버의 기본 URL
const URL = '/practice/todos';

// 즉시실행함수
(function() {

    // ========= 상태관리 변수 및 상수 ========= //
    // 우리 애플리케이션에 필요한 모든 데이터를 하나의 객체 안에 보관합니다.
    const state = {
        // 모든 할 일 데이터를 담는 배열
        todos: [
            {id: 1, text: '자바스크립트 공부하기', completed: true},
            {id: 2, text: '저녁 장보기', completed: false}
        ],
        // 현재 사용자가 어떤 하단 필터(모두-all, 진행중-active, 완료-completed)를 선택했는지
        currentFilter: 'all',
    }

    // ========= DOM 가져오기 ========= //
    const $todoForm = document.getElementById('todo-form');
    const $todoInput = document.getElementById('todo-input');
    const $todoList = document.getElementById('todo-list');
    const $todosLeftCount = document.getElementById('todos-left-count');
    const $filters = document.querySelector('.filters');
    const $clearCompletedBtn = document.getElementById('clear-completed');

    // ========= 핵심 로직 함수 정의 ========= //

    /*
    * @description 현재 state객체를 기반으로 화변을 랜더링하는 함수
    */
    function render(){
        let todosToRender = [];
        // 현재 선택한 필터에 맞게 배열을 재조정
        // 할 일 배열을 순회하여 태그를 동적으로 삽입

        switch (state.currentFilter) {
            case 'all':
                todosToRender = state.todos;
                break;
            case 'active':
                todosToRender = state.todos.filter(todo => todo.completed === false);
                break;
            case 'completed':
                todosToRender = state.todos.filter(todo => todo.completed === true);
                break;
        }

        // 기존 ul의 자식 삭제
        $todoList.innerHTML = ` `;

        todosToRender.forEach(({id,text, completed}) => {
            const $li = document.createElement('li');
            $li.classList.add('todo-item')
            //
            if(completed) $li.classList.add('completed');
            // id를 태그에 심어서 나중에 삭제나 수정할 때 사용
            $li.dataset.id = id;

            $li.innerHTML = `
            <div class="item-container">
                <input type="checkbox" class="todo-check" ${completed ? 'checked' : ''}>
                <span class="todo-text">${text}</span>
                <button class="delete-button">
                    <i class="fas fa-trash-alt"></i>
                </button>
            </div>
          `;
            $todoList.append($li);

        })
        // 남은 할 일 개수를 계산해서 화면에 업데이트
        $todosLeftCount.textContent = `${state.todos.filter(todo => !todo.completed).length}`;
        // 현재 활성화된 버튼에 active 클래스를 제공
        document.querySelectorAll('.filters button').forEach(btn => {
            if(btn.id === `filter-${state.currentFilter}`){
                btn.classList.add('active');
            }
            else{
                btn.classList.remove('active');
            }
        })
    }

    // 완료된 할 일 목록 지우기 이벤트
    function clearCompleteTodos(){
        state.todos = state.todos.filter(todo => !todo.completed)
        render();
    }

    /*
    * @description 새로운 할 일을 상태배열에 추가하는 함수
    * @param newText {string} - 새로 추가할 할 일의 제곡
    */
    function addTodo(newText){
        state.todos.push({
            id: Date.now(),
            text: newText,
            completed: false
        });
        // 데이터가 변경될 때마다 리랜더링 명령
        render();
    }
    /*
    * @description 클릭한 할 일을 삭제하는 함수
    * @param targetId 클릭한 li 태그가 갖고있던 id
    */
    function deleteTodo(targetId){
        // state 배열에 있는 id가 일치하는 객체의 인덱스 찾기
        /*const idx = state.todos.findIndex(todo => todo.id === targetId);
        // 배열에서 제거
        state.todos.splice(idx, 1);*/
        state.todos = state.todos.filter(todo => todo.id !== targetId);
        render();
    }
    /*
    * @description 할 일의 완료 상태를 토글하는 함수
    * @param targetId 클릭한 li 태그가 갖고있던 id
    */
    function toggleTodo(targetId){
        // 클릭한 타켓의 아이디가 일치하는 상태 배열의 객체를 탐색
        /*const foundTodo = state.todos.find(todo => todo.id === targetId);
        foundTodo.completed = !foundTodo.completed;*/

        state.todos = state.todos.map(todo =>
            todo.id === targetId ? {...todo, completed: !todo.completed} : todo
        );
        render();
    }


    // ========= 이벤트 리스너 설정 ========= //

    // 필터링 버튼 클릭 이벤트
    $filters.addEventListener('click', e => {
        if(!e.target.matches('button')) return;

        // 여기서 필터를 누르면 랜더링? 여기서 하지 말고
        // 랜더링은 render 함수에게 맡겨라
        // 필터가 뭐가 눌러졌는지만 확인해서 상태값만 변경

        const buttonId = e.target.id;
        state.currentFilter = buttonId.substring(buttonId.indexOf('-')+1);
        // substring은 문자열 자르기
        // 인덱스는 0번부터 시작이라 +1하여 원하는 부분부터 문자열 자르기 시작
        render(); // 바뀐 상태에 맞게 리랜더링 명령
    })

    // 완료된 할 일 목록 지우기 이벤트
    $clearCompletedBtn.addEventListener('click', e => {
        clearCompleteTodos();
    })

    // 새 할 일을 추가하는 이벤트
    $todoForm.addEventListener('submit', e => {
        e.preventDefault();
        const newTodoText = $todoInput.value.trim();

        // 실제로 상태배열에 입력된 데이터 추가
        if(newTodoText.length > 0){
            addTodo(newTodoText);
        }

        // 입력이 끝나면 입력창 비우고 포커스하기
        $todoInput.value = '';
        $todoInput.focus();
    })

    // 할 일 목록에서 특정 할 일을 삭제하는 이벤트
    // 완료 체크 이벤트
    $todoList.addEventListener('click', e => {
        // 클릭한 버튼에 연결되어 있는 li id를 확인
        const todoId = +e.target.closest('.todo-item').dataset.id;

        // 딱 원하는 곳에만 클릭 이벤트 걸때는 matches
        // 한 div(여러 자식이 있는 경우) 를 잡아야 할때는 closet(div)

        if(e.target.matches('.delete-button i')){
            deleteTodo(todoId);
        }
        else if(e.target.matches('.todo-check')){
            toggleTodo(todoId);
        }
    })

    // ========= 실행 코드 ========= //
    render();
})();

