import { useSearchParams } from "react-router-dom";

const Edit = () =>
{
    const [searchParams,setSearchParams] = useSearchParams()
    
    const id = searchParams.get("id")
    console.log("id : ", id);

    const mode = searchParams.get("mode")
    console.log("mode : ", id);

    return (
        <div>
            <h1>Edit</h1>
            <p>이곳은 일기 수정 페이지 입니다.</p>
        </div>
    );
};

export default Edit;
