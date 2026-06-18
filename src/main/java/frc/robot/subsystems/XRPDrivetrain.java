// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.xrp.XRPGyro;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class XRPDrivetrain extends SubsystemBase {
    private static final double kGearRatio = (30.0 / 14.0) * (28.0 / 16.0) * (36.0 / 9.0) * (26.0 / 8.0); // 48.75:1
    private static final double kCountsPerMotorShaftRev = 12.0;
    private static final double kCountsPerRevolution = kCountsPerMotorShaftRev * kGearRatio; // 585.0
    private static final double kWheelDiameterInch = Units.metersToInches(60.0 / 1000.0);

    // The XRP has the left and right motors set to
    // channels 0 and 1 respectively
    private final XRPMotor leftMotor = new XRPMotor(Constants.LEFT_MOTOR_PORT);
    private final XRPMotor rightMotor = new XRPMotor(Constants.RIGHT_MOTOR_PORT);

    // The XRP has onboard encoders that are hardcoded
    // to use DIO pins 4/5 and 6/7 for the left and right
    private final Encoder leftEncoder = new Encoder(Constants.LEFT_ENCODER_A, Constants.LEFT_ENCODER_B);
    private final Encoder rightEncoder = new Encoder(Constants.RIGHT_ENCODER_A, Constants.RIGHT_ENCODER_B);

    // Set up the differential drive controller
    private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotor::set, rightMotor::set);

    private final XRPGyro gyro = new XRPGyro();

    /**
     * Creates a new XRPDrivetrain.
     */
    public XRPDrivetrain() {
        SendableRegistry.addChild(diffDrive, leftMotor);
        SendableRegistry.addChild(diffDrive, rightMotor);

        // Use inches as unit for encoder distances
        leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
        rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
        resetEncoders();
        gyro.reset();

        // Invert right side since motor is flipped
        rightMotor.setInverted(true);
    }

    public void arcadeDrive(double xAxisSpeed, double zAxisRotate) {
        diffDrive.arcadeDrive(xAxisSpeed, zAxisRotate);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        diffDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public int getLeftEncoderCount() {
        return leftEncoder.get();
    }

    public int getRightEncoderCount() {
        return rightEncoder.get();
    }

    public double getLeftDistanceInch() {
        return leftEncoder.getDistance();
    }

    public double getRightDistanceInch() {
        return rightEncoder.getDistance();
    }
         
    public double getAverageDistanceInch() {
        return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
    }

    public double getGyroAngleX() {
        return gyro.getAngleX(); // In Degrees
    }

    public double getGyroAngleY() {
        return gyro.getAngleY(); // In Degrees
    }

    public double getGyroAngleZ() {
        return gyro.getAngleZ(); // In Degrees
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}