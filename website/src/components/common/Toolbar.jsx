export default function Toolbar(props) {
    function add() {
        props.onAdd();
    }
    return (
        <div className="btn-group mt-2">
            <button type="button" className={`btn btn-primary`} style={{backgroundColor: 'gray', borderColor: 'gray'}} onClick={add}>
                Добавить
            </button>
        </div >
    );
}