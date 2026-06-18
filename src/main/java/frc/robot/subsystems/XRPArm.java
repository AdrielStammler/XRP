package frc.robot.subsystems;

import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRPArm extends SubsystemBase {
    private final XRPServo armServo;

    public XRPArm() {
        armServo = new XRPServo(4);
    }

    public void setAngle(double angleDegrees) {
        armServo.setAngle(angleDegrees);
    }

    public Command move(double degrees) {
        return runOnce(() -> setAngle(degrees));
    }
}