import "./featured.scss";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import { CircularProgressbar } from "react-circular-progressbar";
import "react-circular-progressbar/dist/styles.css";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpOutlinedIcon from "@mui/icons-material/KeyboardArrowUpOutlined";

const Featured = () => {
  return (
    <div className="featured">
      <div className="top">
        <h1 className="title">Total Revenue</h1>
        <MoreVertIcon />
      </div>
      <div className="bottom">
        <div className="featuredChart">
          <CircularProgressbar value={40} text={"40%"} strokeWidth={5} />
        </div>
        <p className="title">Total sales made today</p>
        <p className="amount">LKR 4000</p>
      </div>
    </div>
  );
};

export default Featured;

